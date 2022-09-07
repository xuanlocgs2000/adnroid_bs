package com.example.xmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {


    private Button btnDOM, btnSAX;
    private Spinner dropdown;
    private ListView listView;
    private ListAdapter listAdapter;
    private List<Employee> employeeList;
    private List<Employee> employeeListSorted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setOnClick();
    }

    private void init() {
        btnDOM = findViewById(R.id.btnDOM);
        btnSAX = findViewById(R.id.btnSAX);
        listView = findViewById(R.id.listNv);
        dropdown = findViewById(R.id.drop_down_menu);

        String[] items = new String[]{"", "DEV", "Engineer", "Doctor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        employeeList = new ArrayList<>();
        listAdapter = new ListAdapter(employeeList);
        listView.setAdapter(listAdapter);
    }

    private void setOnClick() {
        btnDOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(employeeList != null) {
                    employeeList.clear();
                }
                parseByDOM();
            }
        });

        btnSAX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(employeeList != null) {
                    employeeList.clear();
                }
                parseBySAX();
            }
        });

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>arg0, View view, int arg2, long arg3) {
                if(employeeList != null) {
                    employeeList.clear();
                }
                parseByDOM();
                if(!dropdown.getSelectedItem().equals("")) {
                    String selected_val=dropdown.getSelectedItem().toString();
                    Iterator<Employee> it = employeeList.iterator();
                    while(it.hasNext()) {
                        Employee employee = it.next();
                        if(!employee.getTitle().equals(selected_val)) {
                            it.remove();
                        }
                    }
                    listAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    private void parseByDOM() {
        try {
            InputStream istream = getAssets().open("employees.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(istream);
            NodeList nList = doc.getElementsByTagName("employee");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node= nList.item(i);
                if(node instanceof Element)
                {
                    Element employee=(Element) node;
                    String id=employee.getAttribute("id");
                    String title=employee.getAttribute("title");
                    NodeList listChild= employee.getElementsByTagName("name");
                    String name=listChild.item(0).getTextContent();
                    listChild=employee.getElementsByTagName("phone");
                    String phone=listChild.item(0).getTextContent();

                    employeeList.add(new Employee(Long.valueOf(id), name, phone, title));
                }
                listAdapter.notifyDataSetChanged();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    private void parseBySAX() {
        try{
            XmlPullParserFactory fc=XmlPullParserFactory.newInstance();
            XmlPullParser parser= fc.newPullParser();
            String xmlfile= "E:\\android_add\\bai4_XMLparser\\app\\src\\main\\assets\\employees.xml";
            FileInputStream fIn=new FileInputStream(xmlfile);
            parser.setInput(fIn, "UTF-8");

            int eventType=-1;
            String nodeName;
            String datashow="";
            while(eventType!=XmlPullParser.END_DOCUMENT)//chưa kết thúc
            {
                eventType=parser.next();// bắt đầu duyệt
                switch(eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG://tag mở
                        nodeName=parser.getName();
                        if(nodeName.equals("employee")){
                            datashow+=parser.getAttributeValue(0)+"-";
                            datashow+=parser.getAttributeValue(1)+"-";
                        }
                        else if(nodeName.equals("name")){
                            datashow+=parser.nextText()+"-";
                        }
                        else if(nodeName.equals("phone")){
                            datashow+=parser.nextText()+"-";
                        }
                        break;
                    case XmlPullParser.END_TAG://là tag đóng
                        nodeName=parser.getName();
                        if(nodeName.equals("employee")){
                            datashow+="\n----------------\n";
                            Log.d("TAG", datashow);
                        }
                        break;
                }
            }
        }
        catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
}