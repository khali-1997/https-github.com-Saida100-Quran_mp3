package example.quran.saida.quran_mp3;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RecyclerViewActivity extends AppCompatActivity implements InterfaceSure {
    AdapterRecyclerView adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Sure> sureList;
    List<Sure> sureler;
    DatabaseSure databaseSure;
    SearchView searchView;
    Map<Integer, String> sureMap = new HashMap<>();
    Sure sure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        sureler = new ArrayList<>();
        getMapData();
        addListData(sureMap);

        sureList = new ArrayList<>();
        databaseSure = new DatabaseSure(getApplicationContext());
        sureList = databaseSure.getSureList();
        adapter = new AdapterRecyclerView(sureler, getApplicationContext(), this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);

            return;
        }
        super.onBackPressed();


    }



    @Override
    public void play(String number, String name) {
        Intent intent = new Intent(RecyclerViewActivity.this, MediaPlayer2Activity.class);
        intent.putExtra("number", number);
        intent.putExtra("name", name);
     //   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);


    }


    private void getMapData() {
        sureMap.put(1, "Fatihə");
        sureMap.put(2, "Bəqərə");
        sureMap.put(3, "Ali-İmran");
        sureMap.put(4, "Nisa");
        sureMap.put(5, "Maidə");
        sureMap.put(6, "Ənam");
        sureMap.put(7, "Əraf");
        sureMap.put(8, "Ənfal");
        sureMap.put(9, "Tövbə");
        sureMap.put(10, "Yunus");
        sureMap.put(11, "Hud");
        sureMap.put(12, "Yusif");
        sureMap.put(13, "Rəd");
        sureMap.put(14, "İbrahim");
        sureMap.put(15, "Hicr");
        sureMap.put(16, "Nəhl");
        sureMap.put(17, "İsra");
        sureMap.put(18, "Kəhf");
        sureMap.put(19, "Məryəm");
        sureMap.put(20, "Taha");
        sureMap.put(21, "Ənbiya");
        sureMap.put(22, "Həcc");
        sureMap.put(23, "Muminun");
        sureMap.put(24, "Nur");
        sureMap.put(25, "Furqan");
        sureMap.put(26, "Şuəra");
        sureMap.put(27, "Nəml");
        sureMap.put(28, "Qəsəs");
        sureMap.put(29, "Ənkəbut");
        sureMap.put(30, "Rum");
        sureMap.put(31, "Loğman");
        sureMap.put(32, "Səcdə");
        sureMap.put(33, "Əhzab");
        sureMap.put(34, "Səba");
        sureMap.put(35, "Fatir");
        sureMap.put(36, "Yasin");
        sureMap.put(37, "Saffat");
        sureMap.put(38, "Sad");
        sureMap.put(39, "Zumər");
        sureMap.put(40, "Ğafir");
        sureMap.put(41, "Fussilət");
        sureMap.put(42, "Şura");
        sureMap.put(43, "Zuxruf");
        sureMap.put(44, "Duxan");
        sureMap.put(45, "Casiyə");
        sureMap.put(46, "Əhqaf");
        sureMap.put(47, "Muhəmməd");
        sureMap.put(48, "Fəth");
        sureMap.put(49, "Hucurat");
        sureMap.put(50, "Qaf");
        sureMap.put(51, "Zariyat");
        sureMap.put(52, "Tur");
        sureMap.put(53, "Nəcm");
        sureMap.put(54, "Qəmər");
        sureMap.put(55, "Rəhman");
        sureMap.put(56, "Vaqiə");
        sureMap.put(57, "Hədid");
        sureMap.put(58, "Mucadələ");
        sureMap.put(59, "Həşr");
        sureMap.put(60, "Mumtəhinə");
        sureMap.put(61, "Səff");
        sureMap.put(62, "Cumuə");
        sureMap.put(63, "Munafiqun");
        sureMap.put(64, "Təğabun");
        sureMap.put(65, "Təlaq");
        sureMap.put(66, "Təhrim");
        sureMap.put(67, "Mülk");
        sureMap.put(68, "Nun və ya əl-Qələm");
        sureMap.put(69, "Haqqə");
        sureMap.put(70, "Məaric");
        sureMap.put(71, "Nuh");
        sureMap.put(72, "Cinn");
        sureMap.put(73, "Muzzəmmil");
        sureMap.put(74, "Muddəsir");
        sureMap.put(75, "Qiyamə");
        sureMap.put(76, "İnsan");
        sureMap.put(77, "Mursələt");
        sureMap.put(78, "Nəbə");
        sureMap.put(79, "Naziat");
        sureMap.put(80, "Əbəs");
        sureMap.put(81, "Təkvir");
        sureMap.put(82, "İnfitar");
        sureMap.put(83, "Mutəffifin");
        sureMap.put(84, "İnşiqaq");
        sureMap.put(85, "Buruc");
        sureMap.put(86, "Tariq");
        sureMap.put(87, "Əla");
        sureMap.put(88, "Ğaşiyə");
        sureMap.put(89, "Fəcr");
        sureMap.put(90, "Bələd");
        sureMap.put(91, "Şəms");
        sureMap.put(92, "Leyl");
        sureMap.put(93, "Zura");
        sureMap.put(94, "İnşirah");
        sureMap.put(95, "Tin");
        sureMap.put(96, "Ələq");
        sureMap.put(97, "Qədr");
        sureMap.put(98, "Bəyyinə");
        sureMap.put(99, "Zilzal");
        sureMap.put(100, "Adiyat");
        sureMap.put(101, "Qariə");
        sureMap.put(102, "Təkasur");
        sureMap.put(103, "Əsr");
        sureMap.put(104, "Huməzə");
        sureMap.put(105, "Fil");
        sureMap.put(106, "Qureyş");
        sureMap.put(107, "Maun");
        sureMap.put(108, "Kəvsər");
        sureMap.put(109, "Kafirun");
        sureMap.put(110, "Nəsr");
        sureMap.put(111, "Əbu Ləhəb");
        sureMap.put(112, "İxlas");
        sureMap.put(113, "Fələq");
        sureMap.put(114, "Nas");


    }

    public void addListData(Map<Integer, String> sureMap) {
        for (int i = 1; i < sureMap.size() + 1; i++) {
            sure = new Sure();
            sure.setNumber(String.valueOf(i));
            sure.setName(sureMap.get(i));
            System.out.println("sure=" + sure);
            sureler.add(sure);

        }

    }


}
