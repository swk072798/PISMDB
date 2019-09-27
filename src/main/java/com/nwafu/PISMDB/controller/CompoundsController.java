package com.nwafu.PISMDB.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nwafu.PISMDB.UserRepository;
import com.nwafu.PISMDB.entity.*;
import com.nwafu.PISMDB.service.CompoundsService;
import com.nwafu.PISMDB.service.PathwaysService;
import com.nwafu.PISMDB.service.TargetsService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

//@RestController
@Controller
public class CompoundsController {
    private String search_text;  //临时存放待搜索的文本
    @Autowired
    private CompoundsService compoundsService;
    //    @Autowired
//    private CompoundsDao compoundsDao;
    //CompoundsService compoundsServiceImp=new CompoundsServiceImp();
    @Autowired
    private TargetsService targetsService;
    @Autowired
    private PathwaysService pathwaysService;

    /////////////////文件搜索部分开始/////////////////////

    static Vector<String> data_1 = new Vector<String>();
    static Vector<String> data_2 = new Vector<String>();



    public String readFile(String file_path) {   //读取文件
        String res = "";
        String temp = "";
        File file = new File(file_path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((temp = br.readLine()) != null) {
                res += temp;
//				System.out.println(temp);
            }
//            System.out.println(res);
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    public Vector<String> save_data_to_arr(String str,String lastname) {    //将文件通过TAB分隔，遍历后获取文件中的数据，保存到数组，用来获取.mol2中的数据
        String [] a = str.split("	");
        Vector<String> b = new Vector<String>();
        boolean flag = false;

        for(int i = 0;i<a.length;i++) {
            if(a[i].equals("A1_0(C" + lastname +")")) {
                flag = true;
                i++;
            }
//			System.out.print(i);
            if(flag == true) {
                b.add(a[i]);
//				System.out.print("1");
            }
        }

        return b;
    }

    public Vector<String> save_data_to_arr(String str,String lastname,String sign) {    //将文件通过TAB分隔，遍历后获取文件中的数据，
        //以字符串sign为数据的开始标志符，将数据保存到数组，用来获取.smile中的数据
        String [] a = str.split("	");
        Vector<String> b = new Vector<String>();
        boolean flag = false;

        for(int i = 0;i<a.length;i++) {
            if(a[i].equals(sign)) {
                flag = true;
                i++;
            }

//			System.out.print(i);
            if(flag == true) {
                b.add(a[i]);
//				System.out.print("1");
            }
        }

        return b;
    }


    public float caculate(Vector<String> a,Vector<String> b) { 			//  sum(xi+yi)/(sum(xi^2)+sum(yi^2)-sum(xiyi)【系数的计算公式】
        float sum_xiyi = 0;
        int i;
        for(i = 0;i<a.size();i++) {
            sum_xiyi += Float.parseFloat(a.elementAt(i)) * Float.parseFloat(b.elementAt(i));
        }

        float sum_xi2 = 0;
        for(i = 0;i<a.size();i++) {
            sum_xi2 += Float.parseFloat(a.elementAt(i)) * Float.parseFloat(a.elementAt(i));  //xi平方的和
        }

        float sum_yi2 = 0;
        for(i = 0;i<b.size();i++) {
            sum_yi2 += Float.parseFloat(b.elementAt(i)) * Float.parseFloat(b.elementAt(i));  //yi的平方和
        }

        float result = sum_xiyi/(sum_xi2 + sum_yi2 - sum_xiyi);
        return result;
    }


    public void delete_zero_and_na(){
        for(int i = 0;i<data_1.size();i++) {
            if(
                    data_1.elementAt(i).equals("0") || data_2.elementAt(i).equals("0") ||
                            data_1.elementAt(i).equals("na") || data_2.elementAt(i).equals("na")
                    ) {
                data_1.remove(i);
                data_2.remove(i);
                i--;
            }


        }
    }

    //    @ResponseBody
//    @RequestMapping("/file_search")
    public void file_search(String file_1,String file_1_lastname){
//        String file_1 = "C:\\Users\\47405\\Desktop\\20R.20R";
//        String file_2 = "C:\\Users\\47405\\Desktop\\20S.20S";
//            String file_1_lastname = "20R";   //更换文件时记得修改,作为mol2文件数据开始的标志符
//        String file_2_lastname = "20S";

        ///////////////////开始计时间////////////////////////
        long startTime = System.currentTimeMillis();
        /////////////
        String path = "D:\\IDEA_pro\\data_resource";
        File file = new File(path);
        File [] fs =  file.listFiles();
        System.out.println("文件个数："+fs.length);
        Vector<String[]> v = new Vector<String[]>();
        ArrayList<Double> al_1 = new ArrayList<Double>();
        ArrayList<String> al_path = new ArrayList<>();

        for(File f : fs){
            String res1 = readFile(file_1);
            String res2 = readFile(f.getPath());

            String file_2_lastname = f.getName().split("\\.")[0];
            data_1 = save_data_to_arr(res1,file_1_lastname);
            data_2 = save_data_to_arr(res2,file_2_lastname);
            delete_zero_and_na();

            double result = caculate(data_1, data_2);
            if(result >0.5){
                al_1.add(result);
                al_path.add(f.getPath());

            }
        }

        System.out.println(al_1.toString());
        System.out.println(al_path.toString());

        long endTime = System.currentTimeMillis();
        System.out.println("计算时间为 ： " + (endTime - startTime));

    }


    //    @ResponseBody
    @RequestMapping("/file_upload")
    //@RequestParam("uploadFile")MultipartFile uploadFile
    public String uploadfile(MultipartFile uploadFile, HttpServletRequest request) throws IOException {
//        String file_path = request.getParameter("uploadFile");
        String fileName = uploadFile.getOriginalFilename();
        String newFileName = fileName;
        String path = "D:\\upload_file_list\\";
        if(null == fileName){
            System.out.println("找不到文件");
            return "找不到文件";
        }
        else{
            uploadFile.transferTo(new File(path+newFileName));
            System.out.println("上传成功！");
            file_search(path+fileName,newFileName.split("\\.")[0]);
            return "Search-sequence-Result";
        }

    }



//////////////文件搜索部分结束///////////////////////////

    @RequestMapping("/browse-T")
    @ResponseBody
    public List<Targets> showTargets1() {
        List<Targets> list = targetsService.findTargetById();
        System.out.println("data数据" + list.size());

        return list;
    }
    /**
     * 分页获取邮箱为指定内容的数据
     *
     * @return
     */
    ///////////搜索引擎部分开始/////////////////////
//    @Test
//    @ResponseBody
//    @RequestMapping("/list")
    public void createIndex() throws Exception {
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
    }
    //content中的值  id,chemicalNames,IUPAC_Name,ChemicalFormular,


    @RequestMapping("/Test_search")
    @ResponseBody
    public List<Compounds> searchIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String text = request.getParameter("search_text");
        search_text += "*";
        System.out.println(search_text);
        Directory directory = FSDirectory.open(new File("D:\\IDEA_pro\\PISMDB").toPath());
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        Analyzer analyzer = new StandardAnalyzer();
        long startTime = System.currentTimeMillis();
//        Query query = new WildcardQuery(new Term("PISMID","*pis*"));
//        //  查询对象，查询结果返回的最大数
        QueryParser queryParser = new QueryParser("Content", new StandardAnalyzer());
        Query query = queryParser.parse(search_text);

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


    @RequestMapping(value = "/search_result")
    public String xianshi(HttpServletRequest request) {
        search_text = request.getParameter("search_text");
        System.out.println(search_text);
        return "Search-sequence-Result";
    }

    /////////////////////搜索引擎部分结束////////////////////////


    @RequestMapping("/listCompounds")
    public PageInfo listCompounds(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        //1. 在参数里接受当前是第几页 start ，以及每页显示多少条数据 size。 默认值分别是0和5。
        //2. 根据start,size进行分页，并且设置id 倒排序
        PageHelper.startPage(start, size);
        //3. 因为PageHelper的作用，这里就会返回当前分页的集合了
        List<Compounds> cs = compoundsService.findById();
        //4. 根据返回的集合，创建PageInfo对象
        PageInfo<Compounds> page = new PageInfo<Compounds>(cs);
        //5. 把PageInfo对象扔进model，以供后续显示
        m.addAttribute("page", page);
//        //6. 跳转到listCategory.jsp
        return page;
    }

    @RequestMapping("/browse-C")
    @ResponseBody
    public List<CompoundsBasicInformationBean> showCompounds1() {
        List<CompoundsBasicInformationBean> list = compoundsService.FindBasicInformation();
        System.out.println("data数据" + list.size());

        return list;
    }


    @RequestMapping("/index")
    public String getCount(HttpServletRequest request) {
        request.setAttribute("compoundsCount", compoundsService.getCompoundsCount());
        //System.out.print(compoundsService.getCompoundsCount());
        request.setAttribute("targetsCount", targetsService.getTargetsCount());
        request.setAttribute("pathwaysCount", pathwaysService.getPathwaysCount());
        return "index";
    }

    @RequestMapping("/third")
    public void showCompoundinfomation(HttpServletRequest request) {
        // request.setAttribute("ZS", compoundsService.getCompoundsCount());
        request.setAttribute("basicinformation", compoundsService.FindBasicInformation());
        //List<Compounds> list=compoundsService.findById();
        //return null;
    }


    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    //    @GetMapping("/compounds")
//    public String greetingForm(Model model) {
//        model.addAttribute("compounds", new Compounds());
//        System.out.println("上传数据1"+ model.toString());
//
//        return "compounds";
//    }
    @RequestMapping("/greeting")
    public String greetingForm123(Model model) {
        model.addAttribute("compounds", new Compounds());
        return "upload";
    }

    static int pid=30;
    @RequestMapping(value = "/upload_data", method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Compounds compounds/*HttpServletRequest request*/) {
        //  System.out.println("sfjasklfdaj");

        Compounds newCompounds = new Compounds();
        newCompounds.setPISMID(String.valueOf(pid));
//        newCompounds.setVersion(compounds.getVersion());
        newCompounds.setChemicalNames(compounds.getChemicalNames());
        newCompounds.setIUPAC_Name(compounds.getIUPAC_Name());
        newCompounds.setSmiles(compounds.getSmiles());
       pid++;
       System.out.println(newCompounds.getPISMID());
//        System.out.println(compounds.getChemicalNames());
//        System.out.println(compounds.getSmiles());
        //newCompounds.setSmiles(compounds.getSmiles());
//      newCompounds.setPISMID(request.getParameter("PISMID"));
        userRepository.save(newCompounds);
        //System.out.println("上传数据2"+ newCompounds.getPISMID());
        return "result";

    }

    @GetMapping("/all")
    public String getMessage(Model model) {

        Iterable<Compounds> compounds = userRepository.findAll();

        model.addAttribute("compounds", compounds);
        System.out.println("上传数据3" + compounds.toString());
        return "all";
    }

    @RequestMapping("/show")
    @ResponseBody
    public List<BrowsePathways> showPics(HttpServletRequest request) {
        List<Pictures> list = compoundsService.showPictureInformation();
        List<Pic> list1 = compoundsService.showPictures();
        List<BrowsePathways> pathways=new ArrayList<>();
        BrowsePathways p1=new BrowsePathways();

        p1.setPic(list1.get(0));
        p1.setPictures(list);
        pathways.add(p1);
        int size = list.size();
        System.out.println(size);
        return pathways;


     /*   Pictures pic[]=new Pictures[size];

        for(int i=0;i<size;i++)
        {
            float startX=list.get(i).getStartX();
            float startY=list.get(i).getStartY();
            float endX=list.get(i).getEndX();
            float endY=list.get(i).getEndY();
            //String url=list.get(i).getUrl();
            String information=list.get(i).getInformation();

            pic[i]=new Pictures(startX,startY,endX,endY,information);
            System.out.println(pic[i].toString());

            //request.setAttribute("p1",pic[i]);
        }
//        float startX=list.get(0).getStartX();
//        float startY=list.get(0).getStartY();
//        float endX=list.get(0).getEndX();
//        float endY=list.get(0).getEndY();
//        String information=list.get(0).getInformation();
//
//        String url=list.get(0).getUrl();
//
//        Pictures p1=new Pictures();
//        p1.setStartX(startX);
//        p1.setStartY(startY);
//        p1.setEndX(endX);
//        p1.setEndY(endY);
//        p1.setUrl(url);
//
//        Pictures p2=new Pictures(url,startX,startY,endX,endY,information);
//
//        request.setAttribute("url",url);
//        request.setAttribute("startX",startX);
//        request.setAttribute("startY",startY);
//        request.setAttribute("endX",endX);
//        request.setAttribute("endY",endY);
//
//        request.setAttribute("p1",p1);
        return list;*/
    }

}

