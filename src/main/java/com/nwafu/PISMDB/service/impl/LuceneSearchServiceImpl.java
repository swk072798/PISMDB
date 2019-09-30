package com.nwafu.PISMDB.service.impl;

import com.nwafu.PISMDB.entity.Compounds;
import com.nwafu.PISMDB.entity.CompoundsBasicInformationBean;
import com.nwafu.PISMDB.service.CompoundsService;
import com.nwafu.PISMDB.service.LuceneSearchService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
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
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: PISMDB
 * @description: 搜索引擎的搭建和关键字搜索
 * @author: zxb
 * @create: 2019-09-30 15:52
 **/

@Service
public class LuceneSearchServiceImpl implements LuceneSearchService {

    @Autowired
    CompoundsService compoundsService;

    @Override
    public void createIndex() throws IOException {
        //把索引库保存到磁盘上
        Directory directory = FSDirectory.open(new File("D:\\IDEA_pro\\PISMDB").toPath());
        //会在index中生成索引目录
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(analyzer));
        List<CompoundsBasicInformationBean> list = compoundsService.FindBasicInformation();
        System.out.println(list.size());
        for (CompoundsBasicInformationBean cbi : list) {
            String PISMID_ = cbi.getPISMID();
            String IUPAC_Name_ = cbi.getIUPAC_Name();
            String ChemicalFormular_ = cbi.getChemicalFormular();
            String AlogP_ = cbi.getAlogP();
            String Smiles_ = cbi.getSmiles();
            String ChemicalNames_ = cbi.getChemicalNames();
            String path_ = cbi.getAddress();
            String content = PISMID_ + " " + ChemicalNames_ + " " + IUPAC_Name_ + " " + ChemicalFormular_ + " " + Smiles_ + " " + AlogP_ + " " + path_;
            System.out.println(content);

            //创建域    域的名称、域的值、是否存储到磁盘
            Field fieldPISMID_ = new TextField("PISMID", PISMID_, Field.Store.YES);
            Field fieldContent = new TextField("Content", content, Field.Store.YES);
//            Field fieldIUPAC_Name_ = new TextField("IUPAC_Name",IUPAC_Name_,Field.Store.YES);
//            Field fieldChemicalFormular_ = new TextField("ChemicalFormular",ChemicalFormular_,Field.Store.YES);
//            Field fieldAlogP_ = new TextField("AlogP",AlogP_,Field.Store.YES);
//            Field fieldSmiles_ = new TextField("Smiles",Smiles_,Field.Store.YES);
//            Field fieldChemicalNames_ = new TextField("ChemicalNames",ChemicalNames_,Field.Store.YES);
            //创建文档对象
            Document document = new Document();
            document.add(fieldPISMID_);
            document.add(fieldContent);

            indexWriter.addDocument(document);
        }
        indexWriter.close();
    }//content中的值  id,chemicalNames,IUPAC_Name,ChemicalFormular,

    @Override
    public List<Compounds> searchIndex(String keyword) throws IOException, InvalidTokenOffsetsException, ParseException {
        System.out.println(keyword);
        Directory directory = FSDirectory.open(new File("D:\\IDEA_pro\\PISMDB").toPath());
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        Analyzer analyzer = new StandardAnalyzer();
        long startTime = System.currentTimeMillis();
//        Query query = new WildcardQuery(new Term("PISMID","*pis*"));
//        //  查询对象，查询结果返回的最大数
        QueryParser queryParser = new QueryParser("Content", new StandardAnalyzer());
        Query query = queryParser.parse(keyword);

        TopDocs topDocs = indexSearcher.search(query, 10);

        System.out.println("查询总数量为 ：" + topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        QueryScorer scorer=new QueryScorer(query);
        Fragmenter fragmenter=new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><fontcolor='red'>","</font></b>");
        Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);
        List<Compounds> list = new ArrayList<Compounds>();

        for (ScoreDoc doc : scoreDocs) {
            int docId = doc.doc;
//            String str = "";
            //根据文档id 获取文档对象
            Document document = indexSearcher.doc(docId);
            System.out.println(document.get("PISMID"));
            TokenStream tokenStream = analyzer.tokenStream("Content", new StringReader(document.get("Content")));
            // String context=highlighter.getBestFragment(tokenStream,document.get("Content"));
            String s=null;
            String foodname=document.get("Content");

            s=highlighter.getBestFragment(analyzer,"Content",document.get("Content"));


            if(s.equals(null)){
                System.out.println("为空");
            }else
//                System.out.println("s是下面这个"+s);
                System.out.println(document.get("Content"));
            //String[] str = document.get("Content").split(" ");
            String[] str = s.split(" ");
            System.out.println("strNumber"+str.length);
            for(int i=0;i<str.length;i++){
                String regex="<b><font";
                str[i]=str[i].replaceAll(regex,"<b><font ");
                System.out.println("第"+i+str[i]);
            }
            System.out.println("s是下面这个\n"+s);
            Compounds compounds = new Compounds();
            // System.out.println("到了3");
            compounds.setPISMID(str[0]);//
            // System.out.println("到了4");
            compounds.setChemicalNames(str[1]);//
            compounds.setIUPAC_Name(str[2]);//
            compounds.setChemicalFormular(str[3]);//
            compounds.setMolecularWeight(str[4]);
            compounds.setAlogP(str[5]);//
            compounds.setAddress(str[6]);

            list.add(compounds);

        }

        System.out.println("数组大小：" + list.size());
        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间：" + (endTime - startTime));
        return list;
    }
}
