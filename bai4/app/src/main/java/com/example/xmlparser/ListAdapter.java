package com.example.xmlparser;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private List<Employee> listEmployee;

    public ListAdapter(List<Employee> listEmployee) {
        this.listEmployee = listEmployee;
    }

    @Override
    public int getCount() {
        if(listEmployee != null) {
            return  listEmployee.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return (Employee) listEmployee.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listEmployee.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewEmployee;
        if (view == null) {
            viewEmployee = View.inflate(viewGroup.getContext(), R.layout.employee_view, null);
        } else viewEmployee = view;

        //Bind sữ liệu phần tử vào View
        Employee employee = (Employee) getItem(i);
        long id = employee.getId();
        String name = employee.getName();
        String phone = employee.getPhone();
        ((TextView) viewEmployee.findViewById(R.id.tvInfo)).setText(id + " - " + name + " - " + phone);


        return viewEmployee;
    }
}
