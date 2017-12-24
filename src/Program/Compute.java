package Program;

import java.util.ArrayList;

import Value.Data;
import Value.Indicators;
import Value.Station;
import Value.Result;

public class Compute {

	private Data data = Data.getInstance();
	private Result result = Result.getInstance();
	
	private ArrayList<String> title = null;
	private ArrayList<Integer> titleColumn = null;
	private ArrayList<String> station = null;
	private int stationColumn = 0;
	private String noData = "-";
	private Station[] stations;
	
	private void setParameter() {
		this.title = new ArrayList<>();
		this.titleColumn = new ArrayList<>();
		// 取得欄位資訊
		getAllColumn();
		// 取得所有測站
		this.station = getAllStation();
		this.stations = new Station[this.station.size()];
	}
	
	public void setNoData(String noData) {
		this.noData = noData;
	}
	
	public void getComputeResults() {
		// 設定
		setParameter();
		// 取得資料
		getStationData();
		// 回傳結果
		outputResult();
	}
	
	private void getAllColumn() {
		for (int c=0;c<data.getColumn();c++) {
			String value = data.getContent(c, 0);
			switch (value) {
			case "測站":
				this.stationColumn = c;
				break;
			}
			this.title.add(value);
			this.titleColumn.add(c);
		}
	}
	
	private ArrayList<String> getAllStation() {
		ArrayList<String> tmpStation = new ArrayList<>();
		// 取得所有測站不重複之值
		for (int r=1;r<data.getRow();r++) {
			if (!tmpStation.contains(data.getContent(this.stationColumn, r))) {
				tmpStation.add(data.getContent(this.stationColumn, r));
			}
		}
		return tmpStation;
	}

	private void getStationData() {
		for (int s=0;s<this.station.size();s++) {
			this.stations[s] = new Station();
			this.stations[s].station = this.station.get(s); // 加入測站
			this.stations[s].indicators = getIndicatorsAvgValues(s, this.stations[s].station); //取得平均值
		}
	}
	
	private Indicators[] getIndicatorsAvgValues(int stationCount, String station) {
		Indicators[] indicators = new Indicators[data.getColumn()-this.stationColumn-1];
		for (int r=0;r<this.data.getRow();r++) {
			for (int c=this.stationColumn+1;c<this.data.getColumn();c++) {
				if (r==0) {
					indicators[c-this.stationColumn-1] = new Indicators(); // 新增Indicators物件
					indicators[c-this.stationColumn-1].matter = data.getContent(c, r); // 取得污染物名稱
				} else {
					// 搜尋相同測站之數值，若值等於NA則略過
					if (!data.getContent(c, r).equals(noData) && (station.equals(data.getContent(this.stationColumn, r)))) {
						indicators[c-this.stationColumn-1].value += Double.parseDouble(data.getContent(c, r));
						indicators[c-this.stationColumn-1].count++;
					}
				}
			}
		}
		for (int i=0;i<indicators.length;i++) {
			double tmpDouble = indicators[i].value/indicators[i].count;
			int tmpInt = (int)Math.round(tmpDouble*100);
			indicators[i].value = ((double)tmpInt)/100.0;
		}
		return indicators;
	}

	private void outputResult() {
		this.result.setRow(this.stations.length+1);
		this.result.setColumn(this.stations[0].indicators.length + 1);
		this.result.setContentSize();
		for (int r=0;r<this.result.getRow();r++) {
			for (int c=0;c<this.result.getColumn();c++) {
				if (r == 0) {
					if (c==0) {
						this.result.setContent(c, r, "Station");
					} else {
						this.result.setContent(c, r, this.stations[0].indicators[c-1].matter);
					}
				} else {
					if (c==0) {
						this.result.setContent(c, r, this.stations[r-1].station);
					} else {
						this.result.setContent(c, r, String.valueOf(this.stations[r-1].indicators[c-1].value));
					}
				}
			}
		}
	}
}
