package co.com.cyxtera.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.MenuItem;




@ManagedBean(name="prin")
@SessionScoped
public class PrincipalControl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String usLoged;
	private List<Integer> listYears;
	private int periodo;
	private int cont=0;
//	private Funcionario us;
	
	

	public PrincipalControl() {
		listYears=new ArrayList<Integer>();
	}
	 public void cambio(ValueChangeEvent e){ 
		 Calendar c=Calendar.getInstance();
		 int year=c.get(Calendar.YEAR);
		 if((Integer)e.getNewValue()==0){
		 }else{
			 if(year==(Integer)e.getNewValue()){
				 c.setTime(new Date());
					int perdiodoActual=c.get(Calendar.MONTH);
			 }
		 }
	 }
	 
	 public void opcionMenu(ActionEvent event){
		 try{
			 MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
			 String url=menuItem.getParams().get("url").get(0);
			 if(cont==0){
				 FacesContext.getCurrentInstance().getExternalContext().redirect(".."+url);
				 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("repo");
				 cont=1;
			 }else{
					 FacesContext.getCurrentInstance().getExternalContext().redirect("../"+".."+url);
					 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("repo");
					 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("rots");
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 public void logout(){
		 try{
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("log");
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("admin");
			 FacesContext.getCurrentInstance().getExternalContext().redirect("../../login/login.faces");
		 }catch (Exception e) {
			e.printStackTrace();
		}
	 }

	
	public int getPeriodo() {
		return periodo;
	}
	public String getUsLoged() {
		return usLoged;
	}

	public List<Integer> getListYears() {
		return listYears;
	}

	public void setListYears(List<Integer> listYears) {
		this.listYears = listYears;
	}
	
	
}

