package pro30;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;

public class MyTransferListener implements FTPDataTransferListener {

	public void started() {
		System.out.println("��ʼ����");// Transfer started
	}

	public void transferred(int length) {
		System.out.println("���д���");// Yet other length bytes has been transferred since the last time this
		// method was called
	}

	public void completed() {
		System.out.println("�������");// Transfer completed
	}

	public void aborted() {
		System.out.println("�����ж�");// Transfer aborted
	}

	public void failed() {
		System.out.println("ʧ�ܴ���");// Transfer failed
	}

}