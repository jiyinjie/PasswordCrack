package passwordCrack;

public class Password {
	private String account;
	private String passwordEncrypted;
	private String salt;
	private String uid;
	private String gid;
	private String fullname;
	private String homedir;
	private String shell;
	
	public Password(String input){
		String[] parts = input.split(":");
		account = parts[0];
		salt = parts[1].substring(0,2);
		passwordEncrypted = parts[1];
		uid = parts[2];
		gid = parts[3];
		fullname = parts[4];
		//homedir = parts[5];
		//shell = parts[6];
	}

	public String getAccount() {
		return account;
	}

	public String getPasswordEncrypted() {
		//TODO: Band-aid fix. Better to parse passwordEncrypted correctly in constructor
		return passwordEncrypted; 
	}

	public String getSalt() {
		return salt;
	}

	public String getUid() {
		return uid;
	}

	public String getGid() {
		return gid;
	}

	public String getFullname() {
		return fullname;
	}

	public String getHomedir() {
		return homedir;
	}

	public String getShell() {
		return shell;
	}
	
	
}
