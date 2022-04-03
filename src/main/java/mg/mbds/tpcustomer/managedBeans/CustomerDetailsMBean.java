/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.mbds.tpcustomer.managedBeans;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import mg.mbds.tpcustomer.entities.Customer;
import mg.mbds.tpcustomer.entities.DiscountCode;
import mg.mbds.tpcustomer.session.CustomerManager;
import mg.mbds.tpcustomer.session.DiscountCodeManager;


/**
 *
 * @author Hajalalaina
 */
@Named
@ViewScoped
public class CustomerDetailsMBean implements Serializable{
   private int idCustomer;
    private Customer customer;
    private List<DiscountCode> discountCodes;
    private Converter<DiscountCode> converterDiscountCode;
    
    @EJB
    private CustomerManager customerManager;
    @EJB
    private DiscountCodeManager discountManager;
    public int getIdCustomer(){
        return idCustomer;
    }
    public void setIdCustomer(int idCustomer){
        this.idCustomer = idCustomer;
    }
    public Customer getDetails(){
        return customer;
    }
    public String update(){
        customer = customerManager.update(customer);
        return "CustomerList";
    }
    public void loadCustomer(){
        this.customer = customerManager.getCustomer(idCustomer);
    }
    public List<DiscountCode> getDiscountCodes(){
        if(discountCodes == null){
            discountCodes = discountManager.getAllDiscountCodes();
        }
        return discountCodes;
    }
    public Converter<DiscountCode> getDiscountCodeConverter(){
        converterDiscountCode = new Converter<DiscountCode>(){
            @Override
            public DiscountCode getAsObject(FacesContext context, UIComponent component, String value){
                return discountManager.findById(value);
            }
            @Override
            public String getAsString(FacesContext context, UIComponent component, DiscountCode value){
                return value.getDiscountCode();
            }
        };
        return converterDiscountCode;
    }
}