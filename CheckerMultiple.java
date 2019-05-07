package checksum;

import java.io.File;

public class CheckerMultiple {

	public static void main(String[] args) throws Exception {
		CheckerMultiple instance = new CheckerMultiple();
		if (args.length >= 1) {
			instance.check(args[0]);
		} else {
			instance.usage();
		}
	}

	private void usage() {
		System.out.println("Usage: folder");
	}

	private void check(String folderName) throws Exception {
		Checker checker = new Checker();
		File folder = new File(folderName);
		if (folder.isDirectory()) {
			for (File f : folder.listFiles()) {
				checker.calculate(f.getAbsolutePath());
				System.out.println();
			}
		}
	}
}