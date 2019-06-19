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
			 * 登录
			 */
			client.setSSLSocketFactory(sslSocketFactory);
			client.setSecurity(FTPClient.SECURITY_FTPES);
			System.out.println("请输入你的ip");
			a1 = input.a();
			System.out.println("请输入你的端口号");
			b1 = input.b();
			client.connect(a1, b1);
			System.out.println("请输入用户名");
			a2 = input.a();
			System.out.println("请输入密码");
			a3 = input.a();
			client.login(a2, a3);
			System.out.println("连接成功 :" + client.toString()); // 服务器信息
			String dir = client.currentDirectory(); // 当前目录
			System.out.println("当前目录为 " + dir);
			client.setType(FTPClient.TYPE_AUTO);// 自动选择传输模式
//			FTPFile[] list = client.list();//查看目录
//			System.out.print(list.toString());

			System.out.println("是否更改目录:" + "0 :不更改   1: 更改");
			b3 = input.b();
			switch (b3) {
			case 1:
				System.out.println("请输入切换的目录" + "eg:/a");
				a5 = input.a();
				client.changeDirectory(a5);
				System.out.println("当前目录为:" + client.currentDirectory());
				break;
			case 0:
				break;
			}
//			client.changeDirectoryUp();//回到上级目录
			System.out.println("输入将要实现的功能" + "1:重命名" + "2:移动文件" + "3:创建目录" + "4:删除目录" + "5:删除文件" + "6:上传文件" + "7:下载文件"
					+ "8:显示文件或文件夹信息" + "9:退出");

			b1 = input.b();
			switch (b1) {
			case 1:
				System.out.println("输入旧文件名");
				a2 = input.a();
				System.out.println("输入新文件名");
				a3 = input.a();
				client.rename(a2, a3);// client.rename("oldname", "newname");
				break;
			case 2:
				System.out.println("输入文件名");
				a4 = input.a();
				System.out.println("输入移动的目录");
				a5 = input.a();
				client.rename(a4, a5);
				break;
			case 3:
				System.out.println("输入创建的目录名称");
				a6 = input.a();
				client.createDirectory(a6);// 创建目录client.createDirectory("newfolder");
				break;
			case 4:
				System.out.println("输入删除的目录名称");
				a7 = input.a();
				client.deleteDirectory(a7);// 创建目录client.deleteDirectory("newfolder");
				break;
			case 5:
				System.out.println("输入删除的目录名称");
				a8 = input.a();
				client.deleteFile(a8);// client.deleteFile("useless.txt");
				break;
			case 6:

				System.out.println("请输入上传文件：" + "格式为F:\\test.txt");
				client.setType(FTPClient.TYPE_BINARY);// 定义编码格式 防止中文出现乱码
				a9 = input.a();
				client.upload(new java.io.File(a9), new MyTransferListener());
				break;
			case 7:

				System.out.println("请输入下载的文件：" + "某个目录下的文件：例如 1.txt");
				client.setType(FTPClient.TYPE_BINARY);// 定义编码格式 防止中文出现乱码
				a10 = input.a();
				System.out.println("请输入本地目录" + "F\\2.txt");
				a11 = input.a();
				client.download(a10, new java.io.File(a11), new MyTransferListener());
				break;
			case 8:
				System.out.println("请输入文件名");
				a12 = input.a();
				java.util.Date md = client.modifiedDate(a12);// 1.txt
				System.out.println(md);
			case 9:
				System.out.println("感谢您的使用");
				client.abruptlyCloseCommunication();
				break;
			}
			client.abruptlyCloseCommunication();
		} catch (Exception e) {
			System.out.println("连接服务器 失败  or 出错");
			e.printStackTrace();
		}
	}
}
