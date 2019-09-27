//package com.nwafu.PISMDB;
//import com.nwafu.PISMDB.service.CompoundsService;
//import org.apache.commons.io.FileUtils;
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.*;
//import org.apache.lucene.search.*;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.junit.Test;
//
//import java.awt.*;
//import java.io.File;
////import java.lang.reflect.Field;
//
////以下是创建索引库的方式
//public class search {
//   // @Test
//    public void  createIndex() throws Exception{
//        //把索引库保存到磁盘上
//        Directory  directory = FSDirectory.open(new File("H:\\IDEA_pro\\lucene_1\\index").toPath());
//        //会在index中生成索引目录
//        Analyzer analyzer = new StandardAnalyzer();
//        IndexWriter indexWriter = new IndexWriter(directory,new IndexWriterConfig(analyzer));
//
//        //File dir = new File("D:\\IDEA_pro\\lucene_1\\
////        File dir=new File("/browse");
////        File [] files = dir.listFiles();
////        @Autowired
////        private CompoundsService compoundsService;
//        for (File f:files){
//            String file_name =  f.getName();
//            String file_path = f.getPath();
//            String file_contect = FileUtils.readFileToString(f,"utf-8");
//            long file_size = FileUtils.sizeOf(f);
//            //创建域    域的名称、域的值、是否存储到磁盘
//            Field fieldName = new TextField("name",file_name,Field.Store.YES);
//         //   Field fieldPath = new TextField("path",file_path,Field.Store.YES);
//            Field fieldContect = new TextField("content",file_contect,Field.Store.YES);
//           // Field  fieldSize = new TextField("size",file_size+"", Field.Store.YES);
//            //创建文档对象
//            Document document = new Document();
//            document.add(fieldName);
//            //document.add(fieldPath);
//            document.add(fieldContect);
//            //document.add(fieldSize);
//
//            indexWriter.addDocument(document);
//        }
//        indexWriter.close();
//    }
//
//    //查询索引的方法
//  //  @Test
//    public void searchIndex() throws Exception{
//        Directory  directory = FSDirectory.open(new File("D:\\IDEA_pro\\lucene_1\\index").toPath());
//        IndexReader indexReader = DirectoryReader.open(directory);
//        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//
//
//        Query query = new WildcardQuery(new Term("content","搜索引擎"));
//        //  查询对象，查询结果返回的最大数
//        TopDocs topDocs = indexSearcher.search(query,10);
//        System.out.println("查询总数量为 ：" +topDocs.totalHits);
//        ScoreDoc []scoreDocs = topDocs.scoreDocs;
//        for(ScoreDoc doc : scoreDocs){
//            int docId = doc.doc;
//            //根据文档id 获取文档对象
//            Document document = indexSearcher.doc(docId);
//            System.out.println(document.get("name"));
//            System.out.println(document.get("path"));
//            System.out.println(document.get("size"));
//            System.out.println(document.get("contene"));
//            System.out.println("-----------分割线");
//
//        }
//    }
//}