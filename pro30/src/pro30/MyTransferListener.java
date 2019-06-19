package pro30;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;

public class MyTransferListener implements FTPDataTransferListener {

	public void started() {
		System.out.println("开始传输");// Transfer started
	}

	public void transferred(int length) {
		System.out.println("进行传输");// Yet other length bytes has been transferred since the last time this
		// method was called
	}

	public void completed() {
		System.out.println("传输完成");// Transfer completed
	}

	public void aborted() {
		System.out.println("传输中断");// Transfer aborted
	}

	public void failed() {
		System.out.println("失败传输");// Transfer failed
	}

}