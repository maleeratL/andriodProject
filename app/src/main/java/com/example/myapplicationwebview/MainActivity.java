package com.example.myapplicationwebview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.webkit.URLUtil;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private String searchText;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        webView = (WebView) findViewById(R.id.webview);
//        //make load in our app not browser app
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("http://www.google.com/search?q="+searchText);
//
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed(){
        if (webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView)searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("testCheck","submit");
                Log.d("testCheck","submit");
                if (onQueryTextChange(searchText)){
                    Log.d("CheckWeb",searchText);
                    if(Patterns.WEB_URL.matcher(searchText).matches()&& URLUtil.isHttpUrl(searchText)){
                        Log.d("CheckWebCondition",searchText);
                        webView = (WebView) findViewById(R.id.webview);
                        //make load in our app not browser app
                        webView.setWebViewClient(new WebViewClient());
                        webView.loadUrl(searchText);
                        WebSettings webSettings = webView.getSettings();
                        webSettings.setJavaScriptEnabled(true);

                    }
                    else{
                        Log.d("CheckWebCondition",searchText);
                        webView = (WebView) findViewById(R.id.webview);
                        //make load in our app not browser app
                        webView.setWebViewClient(new WebViewClient());
                        webView.loadUrl("http://www.google.com/search?q="+searchText);
                        WebSettings webSettings = webView.getSettings();
                        webSettings.setJavaScriptEnabled(true);
//                    searchView.clearFocus();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("testCheck","TextChange");
                searchText = searchView.getQuery().toString();
                if(searchText !=null){
                    return true;
                }

                return false;
            }
        });

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                Log.d("CheckAction","Collapse");
//                setContentView(R.layout.activity_main);
                searchView.setQuery("",true);
                return true;       // Return true to collapse action view
            }
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                Log.d("CheckAction","Expanded");
                return true;      // Return true to expand action view
            }
        });

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this,"Item 1 selected",Toast.LENGTH_SHORT).show();

                return true;
            case R.id.item2:
                Toast.makeText(this,"Item 2 selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                Toast.makeText(this,"Sub Item 1 selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Toast.makeText(this,"Sub Item 2 selected",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
