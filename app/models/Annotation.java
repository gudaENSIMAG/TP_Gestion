package models;

import play.db.jpa.*;
//require
import play.data.validation.*;

import javax.persistence.*;

import java.util.*;

@Entity
@Table(/*uniqueConstraints=
           @UniqueConstraint(columnNames = {"Users", "Products"})*/)
public class Annotation extends Model{
	
	@ManyToMany
	@JoinColumn(name="Users")
	public List<User> users;
	
	@Required
	@ManyToOne 
	@JoinColumn(name="Products")
	public Product product;
//	

	public String note;

	
	@Lob
	public String comment;
	
	public Annotation(Product product, String note, String comment) {
		this.users = new ArrayList<User>();
		this.product = product;
		this.note = note;
		this.comment = comment;
	}

	public Annotation() {

	}
	
	public static List<Annotation> getNotesByUser(User user) {
		return find("select distinct a from Annotation a join a.users as u where u = ?", user).fetch();
	}
	
	public static Annotation getNoteByUserAndProduct(User user, Product product) {
		return find ("byUserAndProduct", user, product).first();
	}

}
