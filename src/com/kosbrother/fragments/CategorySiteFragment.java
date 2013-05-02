package com.kosbrother.fragments;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.taiwan.imageload.GridViewSiteAdapter;
import com.taiwan.imageload.LoadMoreGridView;
import com.travel.story.R;
import com.travel.story.entity.Site;

public class CategorySiteFragment extends Fragment {

    private final ArrayList<Site> sites       = new ArrayList<Site>();
    private LoadMoreGridView      myGrid;
    private GridViewSiteAdapter   myGridViewAdapter;
    private LinearLayout          progressLayout;
    private LinearLayout          loadmoreLayout;
    private LinearLayout          layoutReload;
    private Button                buttonReload;

    String[]                      samplePics  = { "http://www.alaska-in-pictures.com/data/media/13/cruise-ship-scenic_5402.jpg",
            "http://www.alaska-in-pictures.com/data/media/13/kodiak-at-night_5403.jpg" };
    private final Site            samlpleSite = new Site(
                                                      1,
                                                      4,
                                                      "迪士尼度假区（DISNEY LAND",
                                                      "http://www.alaska-in-pictures.com/data/media/13/cruise-ship-scenic_5402.jpg",
                                                      "<ul id=\"Main_divSightDetail\" class=\"view-detail-infor\">\n<li class=\"item\">\n<b>电话：</b>010-65132255</li>\n<li class=\"item\">\n<b>地址：</b>北京市东城区景山前街4号</li>\n<li class=\"item\">\n<b>门票：</b><span>旺季（4/1-10/31）60元/人，淡季(11/1-3/31）40元/人；另外收费的，珍宝馆10元/人，钟表馆10元/人</span>\n</li>\n<li class=\"item\">\n<b>开放时间：</b><span>旺季8:30-17:00，淡季8:30-16:30；</span>\n</li>\n<li class=\"item\">\n<b>交通：</b><span>地铁1号线天安门东、西站可到达，多路公交可到达</span>\n</li>\n<li class=\"item type\">\n<b>类型：</b><em><span href=\"/attractions-d1-s010-rdp1/beijing:attractions.html\">古迹</span>  <span href=\"/attractions-d1-s08-rdp1/beijing:attractions.html\">建筑人文</span>  <span href=\"/attractions-d1-s012-rdp1/beijing:attractions.html\">博物馆</span>  </em>\n</li>\n</ul>\n",
                                                      "<div id=\"Main_divSightIntroduce\" class=\"mod view-instruction\">\n<h2 class=\"hd view-h2\">故宫(紫禁城)简介</h2>\n<div class=\"txt-content\">\n<div class=\"brief-infor\" id=\"brief-info-blk\">        紫禁城（ForbiddenCity）位于北京市中心，是中国明、清两代24个皇帝的皇宫,依照中国古代星象学说，紫微垣（即北极星）位于中天，乃天帝所居，天人对应，是以皇帝的居所又称紫禁城。现称为故宫，意为过去的皇宫。北京紫禁城是当今世界上现存规模最大、建筑最雄伟、保存最完整的古代宫殿和古建筑群。1987年列入《世界遗产名录》。紫禁城东西宽753米，南北长961米，面积达72万平方米，外围是一条宽52米、深6米的...<span class=\"fake-a more-rinfo-down view-more\">更多</span>\n</div>\n<div class=\"all-infor\" id=\"all-infor-blk\">\n<p>紫禁城（Forbidden City）位于北京市中心，是中国明、清两代24个皇帝的皇宫,依照中国古代星象学说，紫微垣（即北极星）位于中天，乃天帝所居，天人对应，是以皇帝的居所又称紫禁城。现称为故宫，意为过去的皇宫。北京紫禁城是当今世界上现存规模最大、建筑最雄伟、保存最完整的古代宫殿和古建筑群。1987年列入《世界遗产名录》。</p>\n<p>紫禁城东西宽753米，南北长961米，面积达72万平方米，外围是一条宽52米、深6米的护城河；河内是周长3公里、高近10米的城墙，城墙四面都有门，南有午门，北有神武门，东有东华门，西有西华门；城墙四角还耸立着4座角楼，造型别致，玲珑剔透。故宫大体可分为两部分，南为工作区，即外朝；北为生活区，即内廷。其所有建筑排列在中轴线上，东西对称，秩序井然。外朝是皇帝处理政事的地方，主要有三大殿：太和殿、中和殿、保和殿。其中太和殿最为高大、辉煌，皇帝登基、大婚、册封、命将、出征等都要在这里举行盛大仪式。太和殿后的中和殿是皇帝出席重大典礼前休息和接受朝拜的地方，最北面的保和殿则是皇帝赐宴和殿试的场所。内廷包括乾清、交泰、坤宁三宫以及东西两侧的东六宫和西六宫，这是皇帝及其嫔妃居住的地方，俗称为“三宫六院”。在居住区以北还有一个小巧别致的御花园，是皇室人员游玩之所。</p>\n<p>现在故宫的一些宫殿中设有综合性展览，收藏有大量古代艺术珍品，共达105万多件，占中国文物总数的1/6，是中国文物收藏最丰富的博物馆，也是世界著名的古代文化艺术博物馆。</p>\n<p>漫游故宫</p>\n<p>中轴线</p>\n<p>午门：是紫禁城的正门，非常雄伟，城楼上的正殿，面廓九间，深五间，正映“九五之尊”。正殿与四个角的方亭，宛如五座山峰，错落有致，所以又叫“五凤楼”。</p>\n<p>太和门：走进午门，前面就是太和门前的广场了。首先映入眼帘的是五座金水桥，站在金水桥上往左看，是通向武英殿的熙和门。这座武英殿，正是李自成匆忙登基的地方，也是摄政王多尔衮办公之地。桥的右边是通向文华殿的协和门，文华殿是清代早期太子的正殿，也是著名的经筵典礼举行的场所。文华殿的后殿就是著名的文渊阁，它是明清两代的宫中图书馆，是仿宁波的天一阁而建的。向正前方看就是太和门了，它与左边的贞度门和右边的昭德门构成了通向三大殿的门户。</p>\n<p>三大殿：太和、中和、保和三大殿，是紫禁城的外朝部分。太和殿，俗称金銮殿，是紫禁城乃至全国最高最大的宫殿，面廓11间，重檐庑殿顶，是朝廷举行重大典礼的地方。中和殿，是一座方亭建筑，它是帝王在太和殿活动时的准备场所，也是帝王在祭祀，和演耕前检查用具的场所。保和殿，是清代除夕为王公大臣设宴的地方，也是清代科举考试最高一级的殿试举行的场所。</p>\n<p>乾清门：连接外朝和内廷的门户，也就是分割前三殿和后三宫的大门，又叫御门。乾清门西侧墙根下的一排朝房就是著名的军机处。</p>\n<p>交泰殿：后三宫的第二殿，建筑形式与中和殿基本相似，但规模略小。明代是皇后的寝宫，清代是皇后生日时接受礼贺的地方。交泰殿宝座东侧古代计时的铜壶滴漏和西侧近代的大自鸣钟是宫中乃至全城的时间基准，神武门和钟鼓楼报时都以此为准。</p>\n<p>坤宁宫：后三宫的第三宫，明代是皇后的寝宫，清代将东二间设为皇帝大婚时的洞房，西五间则改为祭祀萨满教的神堂。清代的顺治，康熙，同治，光绪，溥仪都曾使用过坤宁宫洞房。</p>\n<p>御花园：过坤宁宫，出坤宁门就来到了御花园，御花园内共有殿阁、亭台20多座，古树160多棵，假山水池各2处。钦安殿为御花园的中心建筑，是明代嘉靖皇帝为祭祀道教神灵而建的。堆秀山是院内最高建筑，山上有御景亭，是九九重阳节皇帝登高的地方。园东西两边有千秋亭和万寿亭。御花园的北端为顺贞门，这里就是嘉庆皇帝遇刺的地方，出顺贞门就到了神武门，紫禁城的中轴线就到头了。</p>\n<p>东路</p>\n<p>东六宫：包括钟翠宫，承乾宫，景仁宫，景阳宫，永和宫，延禧宫。其中东太后慈安就住在钟翠宫。另外，延禧宫也非常有名，因为紫禁城中唯一的一座西洋建筑就在延禧宫内，那就是民间广为流传的水晶宫。只可惜延禧宫现在还没有对外开放。</p>\n<p>宁寿宫：整个宫殿群分为前朝和后寝两部分，布局严谨，装饰豪华。其中宁寿宫花园更是远近闻名。目前正在维修，暂时没有开放。宁寿宫花园中最著名的当属流杯亭。每年三月三，皇帝与几位大臣围坐在亭内，将酒杯放在水上，随水而行，行到某人面前停止不动，该人就要饮尽杯中之酒，并赋诗一首。畅音阁是宫中最大的戏台，位于宁寿宫的后寝部分，分三层，最下层210平方米。梅兰芳和谭鑫培等都在这里演过戏。</p>\n<p>珍妃井：位于外东路的最北端，是参观故宫的最后一站。</p>\n<p>西路</p>\n<p>西六宫：包括储秀宫，翊坤宫，永寿宫，咸福宫，长春宫，太极殿。东西六宫每宫都是广深各50米的方格，采取一正两厢，两进院落的格局。西六宫中的长春宫，以红楼梦壁画而闻名。储秀宫至今保持着慈禧太后50寿辰时的原貌，当时慈禧太后不顾国家危难，大肆浪费，将储秀门拆除，与翊坤宫连成一体，成为了四进的大院落，院中装饰奢华令人瞋目。</p>\n<p>养心殿：工字形建筑，分为前朝和后寝，中间以穿堂相连。前殿三间，中间一间为皇帝召见大臣处理政务的地方，东间就是“垂帘听政”的场所。西间有雍正皇帝手书“勤政亲贤”匾，是皇帝召见军机大臣的地方。养心殿的西暖阁有著名的三希堂，由三幅字帖而闻名海内。他们是王羲之的《快雪时晴帖》，王献之的《中秋帖》和王珣的《伯远帖》。三帖中《快雪时晴帖》在台北，另两帖，现藏于故宫。</p>\n<p>慈宁宫：养心殿、西六宫、西五所组成了内西路，在这路建筑的西边还有一路建筑，称为外西路，皇太后所居住的慈宁宫就位于此地。但这一路建筑是不对外开放的。</p>\n<p>漱芳斋：御花园两侧为东五所和西五所，是皇宫内的幼儿园，乾隆皇帝就在西五所的二所居住过，因此这里成为了潜邸，于是乾隆登基后将这里改建为重华宫。重华宫东路就是著名的漱芳斋，漱芳斋内有一座戏台，是仅次于畅音阁大戏台的一所戏台。每年正月初三，乾隆皇帝都在这里开茶话会，招待文武大臣。漱芳斋不对外开放。</p>\n<span class=\"fake-a more-rinfo-up view-less\">隐藏</span>\n</div>\n</div>\n</div>",
                                                      samplePics);

    public static CategoryTravelNoteFragment newInstance() {

        CategoryTravelNoteFragment fragment = new CategoryTravelNoteFragment();

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.loadmore_grid, container, false);
        progressLayout = (LinearLayout) myFragmentView.findViewById(R.id.layout_progress);
        loadmoreLayout = (LinearLayout) myFragmentView.findViewById(R.id.load_more_grid);
        layoutReload = (LinearLayout) myFragmentView.findViewById(R.id.layout_reload);
        buttonReload = (Button) myFragmentView.findViewById(R.id.button_reload);
        myGrid = (LoadMoreGridView) myFragmentView.findViewById(R.id.news_list);
        myGrid.setOnLoadMoreListener(new LoadMoreGridView.OnLoadMoreListener() {
            public void onLoadMore() {

            }
        });

        buttonReload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                progressLayout.setVisibility(View.VISIBLE);
                layoutReload.setVisibility(View.GONE);
                new DownloadChannelsTask().execute();
            }
        });

        if (myGridViewAdapter != null) {
            progressLayout.setVisibility(View.GONE);
            loadmoreLayout.setVisibility(View.GONE);
            myGrid.setAdapter(myGridViewAdapter);
        } else {
            new DownloadChannelsTask().execute();
        }

        return myFragmentView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private class DownloadChannelsTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }

        @Override
        protected Object doInBackground(Object... params) {
            // TODO Auto-generated method stub

            sites.add(samlpleSite);
            sites.add(samlpleSite);
            sites.add(samlpleSite);
            sites.add(samlpleSite);

            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressLayout.setVisibility(View.GONE);
            loadmoreLayout.setVisibility(View.GONE);

            if (sites != null) {
                try {
                    layoutReload.setVisibility(View.GONE);
                    myGridViewAdapter = new GridViewSiteAdapter(getActivity(), sites);
                    myGrid.setAdapter(myGridViewAdapter);
                } catch (Exception e) {

                }
            } else {
                layoutReload.setVisibility(View.VISIBLE);
            }

        }
    }

}
