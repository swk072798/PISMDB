package com.nwafu.PISMDB.service.impl;

import com.nwafu.PISMDB.dao.CompoundsDao;
import com.nwafu.PISMDB.entity.*;
import com.nwafu.PISMDB.service.CompoundsService;
import com.nwafu.PISMDB.service.LuceneSearchService;
import com.nwafu.PISMDB.service.PathwaysService;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: PISMDB
 * @description: 搜索引擎的搭建和关键字搜索
 * @author: liu qinchang
 * @create: 2019-09-30 15:52
 **/
@Slf4j
@Service
public class LuceneSearchServiceImpl implements LuceneSearchService {

    @Autowired
    CompoundsService compoundsService;
    @Autowired
    CompoundsDao compoundsDao;
    @Autowired
    PathwaysService pathwaysService;

    @Override
    public Integer createIndex() throws IOException {      //搜索引擎是将数据库里的coumpounds表中所有文本读出，存储成关键字
        //把索引库保存到磁盘上
        Directory directory = FSDirectory.open(new File("src\\main\\resources\\luceneindex").toPath());
//        Directory directory = FSDirectory.open(new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\PISMDB-0.0.1-SNAPSHOT\\WEB-INF\\classes\\luceneindex").toPath());
        //会在index中生成索引目录
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(analyzer));
        List<CompoundsBasicInformationBean> list = compoundsService.FindBasicInformation();
        System.out.println(list.size());
        for (CompoundsBasicInformationBean cbi : list) {
            log.info("cbi:{}",cbi);
            String PISMID_ = cbi.getPISMID();
            String ChemicalNames_ = cbi.getChemicalNames();
            String IUPAC_Name_ = cbi.getIUPAC_Name();
            String ChemicalFormular_ = cbi.getChemicalFormular();
            String MolecularWeight = cbi.getMolecularWeight();
            String AlogP_ = cbi.getAlogP();
            String content = PISMID_ + " <1> " + ChemicalNames_ + " <1> " + IUPAC_Name_ + " <1> " + ChemicalFormular_ + " <1> " + MolecularWeight + " <1> " + AlogP_ ;
            System.out.println(content);
            //创建域    域的名称、域的值、是否存储到磁盘
            Field fieldPISMID_ = new TextField("PISMID", PISMID_, Field.Store.YES);
            Field fieldContent = new TextField("Content", content, Field.Store.YES);
            //创建文档对象
            Document document = new Document();
            document.add(fieldPISMID_);
            document.add(fieldContent);

            indexWriter.addDocument(document);
        }
        indexWriter.close();
        log.info("搜索引擎创建完成");
        return null;
    }       //content中的值  id,chemicalNames,IUPAC_Name,ChemicalFormular,

    @Override
    public List<FormatData> searchIndex(String keyword) throws IOException, InvalidTokenOffsetsException, ParseException {
        System.out.println(keyword);

//        C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps\PISMDB-0.0.1-SNAPSHOT\WEB-INF\classes\luceneindex

        Directory directory = FSDirectory.open(new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\PISMDB-0.0.1-SNAPSHOT\\WEB-INF\\classes\\luceneindex").toPath());  //服务器端路径
//        Directory directory = FSDirectory.open(new File("D:\\Tomcat\\apache-tomcat-9.0.27\\webapps\\PISMDB-0.0.1-SNAPSHOT\\WEB-INF\\classes\\luceneindex").toPath());//本地tomcat路径
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        Analyzer analyzer = new StandardAnalyzer();
        long startTime = System.currentTimeMillis();
//        Query query = new WildcardQuery(new Term("PISMID","*pis*"));
//        //  查询对象，查询结果返回的最大数
        QueryParser queryParser = new QueryParser("Content", new StandardAnalyzer());
        Query query = queryParser.parse(keyword);
        TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);
        log.info("查询总数量为 ：{}" , topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        QueryScorer scorer=new QueryScorer(query);
        Fragmenter fragmenter=new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><fontcolor='red'>","</font></b>");
        Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);
        List<Compounds> list = new ArrayList<>();
        List<FormatData> formatResult = new ArrayList<>();

        for (ScoreDoc doc : scoreDocs) {
            int docId = doc.doc;
            log.info(Integer.valueOf(docId).toString());
            //根据文档id 获取文档对象
            Document document = indexSearcher.doc(docId);
            String s = null;
            String foodname=document.get("Content");
            log.info("内容为：{}",foodname);
            s = document.get("Content");
            log.info("s的值:{}",s);

            if(s.equals("") || s == null){
                System.out.println("为空");
            }else{
                System.out.println(document.get("Content"));
            }
            String[] str = s.split("<1>");
            log.info("分隔后的值:{}",str);
            for(int i = 0;i < str.length; i++){
                String regex="<b><font";
                str[i] = str[i].replaceAll(regex,"<b><font ");
//                System.out.println("第"+i+str[i]);
            }

            FormatData formatData = new FormatData();
            formatData.setId(str[0]);
            Compounds compounds = compoundsDao.findByPISMID(str[0]);
            formatData.setName(compounds.getChemicalNames());
            CompoundsBasic compoundsBasic = new CompoundsBasic();
            compoundsBasic.setPISMID(compounds.getPISMID());
            compoundsBasic.setAlogP(compounds.getAlogP());
            compoundsBasic.setChemicalFormular(compounds.getChemicalFormular());
            compoundsBasic.setIUPAC_Name(compounds.getIUPAC_Name());
            compoundsBasic.setMolecularWeight(compounds.getMolecularWeight());
            compoundsBasic.setSmiles(compounds.getSmiles());
            formatData.setBasic(compoundsBasic);
            List<String> relatedList = compoundsService.findRelatedById(str[0]);
            formatData.setRelated(new CompoundsRelatedCompounds(str[0],relatedList));
            CompoundsPathway compoundsPathway = new CompoundsPathway();
            formatData.setPathway(compoundsPathway);
            formatData.setIdLink(str[0]);
            formatResult.add(formatData);
        }

        log.info("数组大小：{}" , list.size());
        long endTime = System.currentTimeMillis();
        log.info("消耗时间：{}" ,(endTime - startTime)+"ms");
        return formatResult;
    }

}
