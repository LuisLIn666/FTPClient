package pro30;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPFile;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class FTP4J {

	public static void main(String[] args) {
		try {
			TrustManager[] trustManager = new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };
			SSLContext sslContext = null;
			sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustManager, new SecureRandom());
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			String a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14;
			int b1, b2, b3, b4;
			FTPClient client = new FTPClient();
			Input input = new Input();

//			client.setType(FTPClient.TYPE_AUTO);
			/*
			 * ��¼
			 */
			client.setSSLSocketFactory(sslSocketFactory);
			client.setSecurity(FTPClient.SECURITY_FTPES);
			System.out.println("���������ip");
			a1 = input.a();
			System.out.println("��������Ķ˿ں�");
			b1 = input.b();
			client.connect(a1, b1);
			System.out.println("�������û���");
			a2 = input.a();
			System.out.println("����������");
			a3 = input.a();
			client.login(a2, a3);
			System.out.println("���ӳɹ� :" + client.toString()); // ��������Ϣ
			String dir = client.currentDirectory(); // ��ǰĿ¼
			System.out.println("��ǰĿ¼Ϊ " + dir);
			client.setType(FTPClient.TYPE_AUTO);// �Զ�ѡ����ģʽ
//			FTPFile[] list = client.list();//�鿴Ŀ¼
//			System.out.print(list.toString());

			System.out.println("�Ƿ����Ŀ¼:" + "0 :������   1: ����");
			b3 = input.b();
			switch (b3) {
			case 1:
				System.out.println("�������л���Ŀ¼" + "eg:/a");
				a5 = input.a();
				client.changeDirectory(a5);
				System.out.println("��ǰĿ¼Ϊ:" + client.currentDirectory());
				break;
			case 0:
				break;
			}
//			client.changeDirectoryUp();//�ص��ϼ�Ŀ¼
			System.out.println("���뽫Ҫʵ�ֵĹ���" + "1:������" + "2:�ƶ��ļ�" + "3:����Ŀ¼" + "4:ɾ��Ŀ¼" + "5:ɾ���ļ�" + "6:�ϴ��ļ�" + "7:�����ļ�"
					+ "8:��ʾ�ļ����ļ�����Ϣ" + "9:�˳�");

			b1 = input.b();
			switch (b1) {
			case 1:
				System.out.println("������ļ���");
				a2 = input.a();
				System.out.println("�������ļ���");
				a3 = input.a();
				client.rename(a2, a3);// client.rename("oldname", "newname");
				break;
			case 2:
				System.out.println("�����ļ���");
				a4 = input.a();
				System.out.println("�����ƶ���Ŀ¼");
				a5 = input.a();
				client.rename(a4, a5);
				break;
			case 3:
				System.out.println("���봴����Ŀ¼����");
				a6 = input.a();
				client.createDirectory(a6);// ����Ŀ¼client.createDirectory("newfolder");
				break;
			case 4:
				System.out.println("����ɾ����Ŀ¼����");
				a7 = input.a();
				client.deleteDirectory(a7);// ����Ŀ¼client.deleteDirectory("newfolder");
				break;
			case 5:
				System.out.println("����ɾ����Ŀ¼����");
				a8 = input.a();
				client.deleteFile(a8);// client.deleteFile("useless.txt");
				break;
			case 6:

				System.out.println("�������ϴ��ļ���" + "��ʽΪF:\\test.txt");
				client.setType(FTPClient.TYPE_BINARY);// ��������ʽ ��ֹ���ĳ�������
				a9 = input.a();
				client.upload(new java.io.File(a9), new MyTransferListener());
				break;
			case 7:

				System.out.println("���������ص��ļ���" + "ĳ��Ŀ¼�µ��ļ������� 1.txt");
				client.setType(FTPClient.TYPE_BINARY);// ��������ʽ ��ֹ���ĳ�������
				a10 = input.a();
				System.out.println("�����뱾��Ŀ¼" + "F\\2.txt");
				a11 = input.a();
				client.download(a10, new java.io.File(a11), new MyTransferListener());
				break;
			case 8:
				System.out.println("�������ļ���");
				a12 = input.a();
				java.util.Date md = client.modifiedDate(a12);// 1.txt
				System.out.println(md);
			case 9:
				System.out.println("��л����ʹ��");
				client.abruptlyCloseCommunication();
				break;
			}
			client.abruptlyCloseCommunication();
		} catch (Exception e) {
			System.out.println("���ӷ����� ʧ��  or ����");
			e.printStackTrace();
		}
	}
}
