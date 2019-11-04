package com.nwafu.PISMDB.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nwafu.PISMDB.UserRepository;
import com.nwafu.PISMDB.entity.*;
import com.nwafu.PISMDB.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
@Slf4j
@Api(description = "分子操作Api")
public class CompoundsController {

    private String search_text;  //临时存放待搜索的文本

    @Autowired
    private CompoundsService compoundsService;
    @Autowired
    private TargetsService targetsService;
    @Autowired
    private PathwaysService pathwaysService;
    @Autowired
    private LuceneSearchService luceneSearchService;
    @Autowired
    private FileSearchService fileSearchService;

    /////////////////文件搜索部分开始/////////////////////
    /**
     * 这里是上传文件进行文件搜索
     * @param uploadFile
     * @param request
     * @return
     * @throws IOException
     */
    //这个功能还没完，缺少blast的东西

    @ApiOperation(value = "上传文件搜索", notes = "根据本地的文件搜索")
    @PostMapping("/uploadFileSearch")
    //@RequestParam("uploadFile")MultipartFile uploadFile
    public String uploadfile(MultipartFile uploadFile, HttpServletRequest request) throws IOException {
        String fileName = uploadFile.getOriginalFilename();
        if(null == fileName){
            System.out.println("找不到文件");
            return "找不到文件";
        }
//        String file_path = request.getParameter("uploadFile");
        if(!fileName.split(".")[1].equals(".mol2")){
            System.out.println("文件格式错误");
            return null;
        }
        String newFileName = fileName;
        String path = "D:\\upload_file_list\\";     /**
                                                            这里上传路径要改*/
        uploadFile.transferTo(new File(path+newFileName));
        System.out.println("上传成功！");
        fileSearchService.fileSearch(path+fileName,newFileName.split("\\.")[0]);
        return "Search-sequence-Result";

        /**
         * 上传成功后还应该调用搜索引擎的更新函数和blast+库的重建函数
         */

    }

//////////////文件搜索部分结束///////////////////////////

    @ApiOperation(value = "蛋白质json数据", notes = "从数据库中获取的蛋白质的json数据")
    @GetMapping("/browse-T")
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

    @ApiOperation(value = "创建搜索索引", notes = "创建搜索引擎的索引,不要随便调用")
    @RequestMapping(value = "/createIndex",method = RequestMethod.POST)
    @ResponseBody
    public Integer createIndex() throws Exception {
        log.info("创建搜索引擎中");
        Integer result = luceneSearchService.createIndex();
        return result;
    }
    //content中的值  id,chemicalNames,IUPAC_Name,ChemicalFormular,

    @ApiOperation(value = "搜索引擎关键字查找传参", notes = "搜索引擎关键字查找传参")
    @GetMapping("/keywordSearch")
    @ResponseBody
    public List<Compounds> searchIndex(@RequestParam String search_text) throws Exception {
        System.out.println(search_text);
        log.info("搜索引擎关键字查找传参：{}",search_text);
//        List<Compounds> compoundsList = luceneSearchService.searchIndex("[^\\S]*" + search_text + "*");
        List<Compounds> compoundsList = luceneSearchService.searchIndex(search_text + "*");
//        List<Compounds> compoundsList = luceneSearchService.searchIndex(search_text);
        return compoundsList;
    }

    @ApiOperation(value = "搜索结果", notes = "搜索结果")
    @RequestMapping(value = "/search_result",method = RequestMethod.GET)
    public String xianshi(HttpServletRequest request) {
        search_text = request.getParameter("search_text");
        System.out.println(search_text);
        return "Search-sequence-Result";
    }

    /////////////////////搜索引擎部分结束////////////////////////

    @ApiOperation(value = "分页查询", notes = "分页查询，目前有问题")
    @GetMapping("/listCompounds")
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

    @ApiOperation(value = "分子json数据", notes = "分子的json数据")
    @GetMapping("/browse-C")
    @ResponseBody
    public List<CompoundsBasicInformationBean> showCompounds1() {
        List<CompoundsBasicInformationBean> list = compoundsService.FindBasicInformation();
        System.out.println("data数据" + list.size());

        return list;
    }


    @ApiOperation(value = "系统首页", notes = "跳转到系统的首页")
    @GetMapping("/index")
    public String getCount(HttpServletRequest request) {
        request.setAttribute("compoundsCount", compoundsService.getCompoundsCount());
        request.setAttribute("targetsCount", targetsService.getTargetsCount());
        request.setAttribute("pathwaysCount", pathwaysService.getPathwaysCount());
        return "index";
    }


    @ApiOperation(value = "跳转到分子页面", notes = "跳转到分子页面")
    @GetMapping("/Browse_C")
    public String Browse_C() {
        return "browse-Compound";
    }

    @ApiOperation(value = "跳转到Target页面", notes = "跳转到Target页面")
    @GetMapping("/Browse_T")
    public String Browse_T() {
        return "browse-Target";
    }

    @ApiOperation(value = "跳转到Pathway页面", notes = "跳转到Pathway页面")
    @GetMapping("/Browse_P")
    public String Browse_P() {
        return "browse-Pathway";
    }

    @ApiOperation(value = "跳转到Search_text页面", notes = "跳转到Search_text页面")
    @GetMapping("/Search_text")
    public String Search_text() {
        return "search-text";
    }

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

//        @GetMapping("/compounds")
//    public String greetingForm(Model model) {
//        model.addAttribute("compounds", new Compounds());
//        System.out.println("上传数据1"+ model.toString());
//
//        return "compounds";
//}
    @ApiOperation(value = "文件上传跳转接口", notes = "跳转到文件上传")
    @RequestMapping(value = "/greeting",method = RequestMethod.GET)
    public String greetingForm123(Model model) {
        model.addAttribute("compounds", new Compounds());
        return "upload";
    }

    static int pid=30;
    @ApiOperation(value = "文件上传", notes = "文件上传到数据库")
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

    @ApiOperation(value = "获取所有数据", notes = "获取所有数据")
    @GetMapping("/all")
    public String getMessage(Model model) {

        Iterable<Compounds> compounds = userRepository.findAll();

        model.addAttribute("compounds", compounds);
        System.out.println("上传数据3" + compounds.toString());
        return "all";
    }

    @ApiOperation(value = "显示pathway", notes = "显示pathway")
    @RequestMapping(value = "/show",method = RequestMethod.GET)
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

