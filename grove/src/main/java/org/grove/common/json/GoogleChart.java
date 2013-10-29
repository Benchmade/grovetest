package org.grove.common.json;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

public class GoogleChart {

	public static void main(String[] args) {

		Gson gson = new Gson();

		List<ChartJsonResult> list = new ArrayList<ChartJsonResult>();

		ChartJsonResult c1 = new ChartJsonResult();
		List<Cols> colsList1 = new ArrayList<GoogleChart.Cols>(1);
		colsList1.add(new Cols("pv", "pv好", "number"));
		List<Rows> rowsList1 = new ArrayList<Rows>();
		
		List<RowData> c = new ArrayList<RowData>();
		c.add(new RowData("品牌词"));
		c.add(new RowData(1234));
		
		List<RowData> c2 = new ArrayList<RowData>();
		c2.add(new RowData("类目词"));
		c2.add(new RowData(555));
		
		rowsList1.add(new Rows(c));
		rowsList1.add(new Rows(c2));

		c1.setCols(colsList1);
		c1.setRows(rowsList1);
		
		list.add(c1);
		
		String text = JSON.toJSONString(list);
		System.out.println(text);
		System.out.println(gson.toJson(list));

	}

	static class ChartJsonResult {

		List<Cols> cols = new ArrayList<GoogleChart.Cols>(1);

		List<Rows> rows = new ArrayList<Rows>();

		public List<Cols> getCols() {
			return cols;
		}

		public void setCols(List<Cols> cols) {
			this.cols = cols;
		}

		public List<Rows> getRows() {
			return rows;
		}

		public void setRows(List<Rows> rows) {
			this.rows = rows;
		}

	}

	static class Cols {
		String id;
		String label;
		String type;

		public Cols(String id, String label, String type) {
			this.id = id;
			this.label = label;
			this.type = type;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}

	static class Rows {
		public Rows(List<RowData> c) {
			this.c = c;
		}
		
		List<RowData> c = new ArrayList<RowData>();

		public List<RowData> getC() {
			return c;
		}

		public void setC(List<RowData> c) {
			this.c = c;
		}
	}

	static class RowData {
		public RowData(Object v) {
			this.v = v;
		}
		Object v;

		public Object getV() {
			return v;
		}

		public void setV(Object v) {
			this.v = v;
		}
	}
}
