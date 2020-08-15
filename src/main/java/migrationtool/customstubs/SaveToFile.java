package migrationtool.customstubs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import migrationtool.utils.Configs;
import migrationtool.utils.FileWriteUtils;

public class SaveToFile implements SaveReconcilation {

	@Override
	public void persistSTS(List<STSPojo> listOfSts) {
		FileWriteUtils writer = new FileWriteUtils();
		writer.openFile(Configs.outputPath);
		writer.write("original_distributor_code,istatus,item_code,count");
		System.out.println("Writing to " + Configs.outputPath);
		List<String> putToFile = new ArrayList<String>();
		for (STSPojo data : listOfSts) {
			String record = data.getOriginal_distributor_code() + "," + data.getIstatus() + "," + data.getItem_code()
					+ "," + data.getCount();
			putToFile.add(record);
			if (putToFile.size() > Configs.outputBatchSize) {
				writer.write(putToFile);
				putToFile.clear();
				System.out.println(Configs.outputBatchSize + " Records written to File ");
			}
		}
		writer.write(putToFile);
		writer.close();

	}

	@Override
	public void persistSRIS(List<SRISPojo> listOfSris) {
		FileWriteUtils writer = new FileWriteUtils();
		writer.openFile(Configs.outputPath);
		writer.write("cluster_id,region_id,count");
		System.out.println("Writing to " + Configs.outputPath);
		List<String> putToFile = new ArrayList<String>();
		for (SRISPojo data : listOfSris) {
			String record = data.getCluster_id() + "," + data.getRegion_id() + "," + data.getCount();
			putToFile.add(record);
			if (putToFile.size() > Configs.outputBatchSize) {
				writer.write(putToFile);
				putToFile.clear();
				System.out.println(Configs.outputBatchSize + " Records written to File ");
			}
		}
		writer.write(putToFile);
		writer.close();

	}
}
