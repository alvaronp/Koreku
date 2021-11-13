package es.unex.giiis.koreku;

import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.unex.giiis.koreku.roomdb.DateConverter;

@Entity(tableName="consola", foreignKeys = @ForeignKey(entity = Perfil.class,
											parentColumns = "profile_id",
											childColumns = "profile_id"))
public class Consolas {
	@Ignore
	public static final String ITEM_SEP = System.getProperty("line.separator");
	@Ignore
	public final static String ID = "ID";
	@Ignore
	public final static String TITLE = "title";
	@Ignore
	public final static String DATE = "date";
	@Ignore
	public final static String COMPANY = "company";
	@Ignore
	public final static String IMAGE = "image";

	@Ignore
	public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", Locale.US);
	@Ignore
	public final static String PROFILEID = "PROFILEID";

	@ColumnInfo(name = "console_id")
	@PrimaryKey(autoGenerate = true)
	private long id;
	@ColumnInfo(name="title")
	private String title = new String();
	@TypeConverters(DateConverter.class)
	private Date date = new Date();
	@ColumnInfo(name="company")
	private String company = new String();
	@ColumnInfo(name="image")
	private String image = new String();
	@ColumnInfo(name= "profile_id", index = true)
	private long profileid;

	@Ignore
    Consolas(String title, Date date, String company, String image, long profileid) {
		this.title = title;
		this.date = date;
		this.company=company;
		this.image=image;
		this.profileid=profileid;
	}
	@Ignore
    public Consolas(long ID, String title, String date, String company, String image, long profileid) {
        this.id = ID;
        this.title = title;
        try {
            this.date = Consolas.FORMAT.parse(date);
        } catch (ParseException e) {
            this.date = new Date();
        }
		this.company=company;
		this.image=image;
		this.profileid=profileid;
    }

	@Ignore
    Consolas(Intent intent) {
		id = intent.getLongExtra(Consolas.ID,0);
		title = intent.getStringExtra(Consolas.TITLE);
		try {
			date = Consolas.FORMAT.parse(intent.getStringExtra(Consolas.DATE));
		} catch (ParseException e) {
			date = new Date();
		}
		company = intent.getStringExtra(Consolas.COMPANY);
		image = intent.getStringExtra(Consolas.IMAGE);
		profileid = intent.getLongExtra(Consolas.PROFILEID, 0);
	}


	public Consolas(long id, String title, Date date, String company, String image, long profileid){
		this.id =id;
		this.date =date;
		this.title =title;
		this.company=company;
		this.image=image;
		this.profileid=profileid;
	}
    public long getId() { return id; }

    public void setId(long ID) { this.id = ID; }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getProfileid(){
		return profileid;
	}

	public void setProfileid(long profileid){
		this.profileid = profileid;
	}

	// Take a set of String data values and 
	// package them for transport in an Intent

	public static void packageIntent(Intent intent, String title,
									 String company, String image, String date) {
		intent.putExtra(Consolas.TITLE, title);
		intent.putExtra(Consolas.COMPANY, company.toString());
		intent.putExtra(Consolas.IMAGE, image.toString());
		intent.putExtra(Consolas.DATE, date);
	}

	public String toString() {
		return id + ITEM_SEP + title + ITEM_SEP
				+ FORMAT.format(date)+ITEM_SEP+ company +ITEM_SEP+ image + ITEM_SEP + profileid;
	}

	public String toLog() {
		return "ID: " + id + ITEM_SEP + "Title:" + title + ITEM_SEP + ITEM_SEP + "Date:"
				+ FORMAT.format(date)+ "Company:" + company
				+ ITEM_SEP + "Image:" + image + "Profile ID: " + profileid;
	}

}
