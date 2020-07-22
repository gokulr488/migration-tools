package migrationtool.customstubs;

import java.util.List;

public interface SaveReconcilation {
	public void persistSTS(List<STSPojo> listOfSts);

	public void persistSRIS(List<SRISPojo> listOfSris);
}
