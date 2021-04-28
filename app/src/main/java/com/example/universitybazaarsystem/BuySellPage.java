package com.example.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BuySellPage extends AppCompatActivity implements CustomAdapter.OnProductListener {

    private Button postButton;
    MaterialSearchBar materialSearchBar;
    List<String> suggestionList = new ArrayList<>();
    RecyclerView rv_viewListOfProducts;
    ArrayList<Product> listOfProducts;

    DatabaseHelper dbHelper;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell_page);
        postButton = findViewById(R.id.addPostButton);
        rv_viewListOfProducts = findViewById(R.id.listRecyclerView);
        materialSearchBar = findViewById(R.id.ms_search_bar);
        listOfProducts = new ArrayList<>();
        dbHelper = new DatabaseHelper(this);
        materialSearchBar.setHint("Search");
        loadSuggestionList();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                List<String> suggest = new ArrayList<>();

                for(String search:suggestionList){
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase())){
                        suggest.add(search);
                    }
                }

                materialSearchBar.setLastSuggestions(suggest);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled){

                    rv_viewListOfProducts.setAdapter(customAdapter);

                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {

                getSearchResult(text.toString());

            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
        //pla = new ProductListAdapter(this,R.layout.product_items_layout,listOfProducts);
        //gridView.setAdapter(pla);


       try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {

                e.printStackTrace();

        }

        Cursor cursor = dbHelper.getCursorForProductList();
        while (cursor.moveToNext()){
            long productId = cursor.getLong(0);
            String stuId = cursor.getString(1);
            //System.out.println(stuId);
            String productName = cursor.getString(2);
            //System.out.println(productName);
            String productPrice = cursor.getString(3);
            //System.out.println(productPrice);
            String productDescription = cursor.getString(4);
            //System.out.println(productDescription);
            byte[] productImage = cursor.getBlob(5);

            listOfProducts.add(new Product(stuId,productName,productPrice,productDescription, productId, productImage));

        }

        customAdapter = new CustomAdapter(BuySellPage.this,listOfProducts,this);
        rv_viewListOfProducts.setAdapter(customAdapter);
        rv_viewListOfProducts.setLayoutManager(new LinearLayoutManager(BuySellPage.this));
        

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BuySellPage.this,AddProductActivity.class);
                startActivity(intent);

            }
        });



    }

    private void getSearchResult(String name) {

        customAdapter = new CustomAdapter(BuySellPage.this,new ArrayList(dbHelper.getSearchResult(name)),this);
        rv_viewListOfProducts.setAdapter(customAdapter);


    }

    private void loadSuggestionList() {

        suggestionList = dbHelper.getProductNamesList();
        materialSearchBar.setLastSuggestions(suggestionList);

    }

    @Override
    public void onProductClick(int position) {
        listOfProducts.get(position);
        Intent intent = new Intent(BuySellPage.this,ProductDetails.class);
        intent.putExtra("productId",listOfProducts.get(position).getProductId());
        startActivity(intent);


    }
}