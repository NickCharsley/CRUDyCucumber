/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.steps;

import cucumber.api.java.en.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.tree.TreePath;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jemmy.operators.JPopupMenuOperator;
import org.netbeans.jemmy.operators.JTreeOperator;
import org.openide.util.Exceptions;
import uk.co.oldnicksoftware.crudycucumber.domain.Customer;
import uk.co.oldnicksoftware.crudycucumber.domain.DiscountCode;
import uk.co.oldnicksoftware.crudycucumber.domain.MicroMarket;
import uk.co.oldnicksoftware.crudycucumber.domain.Product;
import uk.co.oldnicksoftware.crudycucumber.domain.PurchaseOrder;

/**
 *
 * @author nick
 */
public class CrudyPUSteps {
    private final EntityManager entityManager;

    public CrudyPUSteps(){
        entityManager = Persistence
                .createEntityManagerFactory("CustomerPU")
                .createEntityManager();
    }    

    private void addPurchaseOrder(Integer ORDER_NUM,Integer CUSTOMER_ID,Integer PRODUCT_ID,
                                  Integer QUANTITY,Double SHIPPING_COST,String SALES_DATE,
                                  String SHIPPING_DATE,String FREIGHT_COMPANY){
        PurchaseOrder po;
        try {
            po=(PurchaseOrder)entityManager
                            .createNamedQuery("PurchaseOrder.findByOrderNum")
                            .setParameter("orderNum",ORDER_NUM)               
                            .getSingleResult();
        } catch (NoResultException e){
            po=new PurchaseOrder();
            po.setOrderNum(ORDER_NUM);
        }
        Customer c=(Customer)entityManager
                            .createNamedQuery("Customer.findByCustomerId")
                            .setParameter("customerId",CUSTOMER_ID)
                            .getSingleResult();      
        Product p=(Product)entityManager
                            .createNamedQuery("Product.findByProductId")
                            .setParameter("productId", PRODUCT_ID)
                            .getSingleResult();
        po.setCustomerId(c);
        po.setProductId(p);
        po.setQuantity(Short.valueOf(QUANTITY.toString()));
        po.setShippingCost(BigDecimal.valueOf(SHIPPING_COST));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            po.setShippingDate(sdf.parse(SHIPPING_DATE));            
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        try {
            po.setSalesDate(sdf.parse(SALES_DATE));            
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        po.setFreightCompany(FREIGHT_COMPANY);
                
        entityManager.getTransaction().begin();
        entityManager.merge(po);
        entityManager.getTransaction().commit();
    }    
    
    private void addCustomer(int CUSTOMER_ID,String DISCOUNT_CODE,String ZIP,
                             String NAME,String ADDRESSLINE1,String ADDRESSLINE2,
                             String CITY,String STATE,String PHONE,String FAX,
                             String EMAIL,int CREDIT_LIMIT){
        Customer c;
        try {
            c=(Customer)entityManager
                            .createNamedQuery("Customer.findByCustomerId")
                            .setParameter("customerId",CUSTOMER_ID)
                            .getSingleResult();      
        } catch (NoResultException e) {
            c=new Customer();
            c.setCustomerId(CUSTOMER_ID);
        }
        DiscountCode dc=(DiscountCode)entityManager
                            .createNamedQuery("DiscountCode.findByDiscountCode")
                            .setParameter("discountCode", DISCOUNT_CODE)
                            .getSingleResult();
        MicroMarket mm=(MicroMarket)entityManager
                            .createNamedQuery("MicroMarket.findByZipCode")
                            .setParameter("zipCode", ZIP)
                            .getSingleResult();
        
        c.setDiscountCode(dc);
        c.setZip(mm);
        c.setName(NAME);
        c.setAddressline1(ADDRESSLINE1);
        c.setAddressline2(ADDRESSLINE2);
        c.setCity(CITY);
        c.setState(STATE);
        c.setPhone(PHONE);
        c.setFax(FAX);
        c.setEmail(EMAIL);
        c.setCreditLimit(CREDIT_LIMIT);

        entityManager.getTransaction().begin();
        entityManager.merge(c);        
        entityManager.getTransaction().commit();

    }

    private void buildCustomers() {
        addCustomer(1,"N","95117","Jumbo Eagle Corp II","111 E. Las Olivas Blvd","Suite 51","Fort Lauderdale","FL","305-555-0188","305-555-0189","jumboeagle@example.com",100000);
        addCustomer(2,"M","95035","Old  Enterprises","9754 Main Street","P.O. Box 567","Miami","FL","305-555-0148","305-555-0149","www.new.example.com",50000);
        addCustomer(25,"M","85638","Wren Computers","8989 Red Albatross Drive","Suite 9897","Houston","TX","214-555-0133","214-555-0134","www.wrencomp.example.com",25000);
        addCustomer(3,"L","12347","Small Bill Company","8585 South Upper Murray Drive","P.O. Box 456","Alanta","GA","555-555-0175","555-555-0176","www.smallbill.example.com",90000);
        addCustomer(36,"H","94401","Bob Hosting Corp.","65653 Lake Road","Suite 2323","San Mateo","CA","650-555-0160","650-555-0161","www.bobhostcorp.example.com",65000);
        addCustomer(106,"L","95035","Early CentralComp","829 E Flex Drive","Suite 853","San Jose","CA","408-555-0157","408-555-0150","www.centralcomp.example.com",26500);
        addCustomer(149,"L","95117","John Valley Computers","4381 Kelly Valley Ave","Suite 77","Santa Clara","CA","408-555-0169","408-555-0167","www.johnvalley.example.com",70000);
        addCustomer(863,"N","94401","Big Network Systems","456 444th Street","Suite 45","Redwood City","CA","650-555-0181","650-555-0180","www.bignet.example.com",25000);
        addCustomer(777,"L","48128","West Valley Inc.","88 Northsouth Drive","Building C","Dearborn","MI","313-555-0172","313-555-0171","www.westv.example.com",100000);
        addCustomer(753,"H","48128","Zed Motor Co","2267 NE Michigan Ave","Building 21","Dearborn","MI","313-555-0151","313-555-0152","www.parts@ford.example.com",5000000);
        addCustomer(722,"N","48124","Big Car Parts","52963 Notouter Dr","Suite 35","Detroit","MI","313-555-0144","313-555-0145","www.bparts.example.com",50000);
        addCustomer(409,"L","10095","Old Media Productions","4400 527th Street","Suite 562","New York","NY","212-555-0110","212-555-0111","www.oldmedia.example.com",10000);
        addCustomer(410,"M","10096","Yankee Computer Repair Ltd","9653 211th Ave","Floor 4","New York","NY","212-555-0191","212-555-0197","www.nycompltd@repair.example.com",25000);        
    }
    
    private void buildPurchaseOrders(){
        addPurchaseOrder(10398001,1,980001,10,449.00,"2011-05-24","2011-05-24","Poney Express");
        addPurchaseOrder(10398002,2,980005,8,359.99,"2011-05-24","2011-05-24","Poney Express");
        addPurchaseOrder(10398003,2,980025,25,275.00,"2011-05-24","2011-05-24","Poney Express");
        addPurchaseOrder(10398004,3,980030,10,275.00,"2011-05-24","2011-05-24","Poney Express");
        addPurchaseOrder(10398005,1,980032,100,459.00,"2011-05-24","2011-05-24","Poney Express");
        addPurchaseOrder(10398006,36,986710,60,55.00,"2011-05-24","2011-05-24","Slow Snail");
        addPurchaseOrder(10398007,36,985510,120,65.00,"2011-05-24","2011-05-24","Slow Snail");
        addPurchaseOrder(10398008,106,988765,500,265.00,"2011-05-24","2011-05-24","Slow Snail");
        addPurchaseOrder(10398009,149,986420,1000,700.00,"2011-05-24","2011-05-24","Western Fast");
        addPurchaseOrder(10398010,863,986712,100,25.00,"2011-05-24","2011-05-24","Slow Snail");
        addPurchaseOrder(20198001,777,971266,75,105.00,"2011-05-24","2011-05-24","We deliver");
        addPurchaseOrder(20598100,753,980601,100,200.99,"2011-05-24","2011-05-24","We deliver");
        addPurchaseOrder(20598101,722,980500,250,2500.00,"2011-05-24","2011-05-24","Coastal Freight");
        addPurchaseOrder(30198001,409,980001,50,2000.99,"2011-05-24","2011-05-24","Southern Delivery Service");
        addPurchaseOrder(30298004,410,980031,100,700.00,"2011-05-24","2011-05-24","FR Express");        
    }
    
    @Given("^I have (an empty|the test) Database$")
    public void haveADatabase(String databaseType) throws Throwable {
        //Need to perform in correct order...
        try {
            //Always empty first
            entityManager.getTransaction().begin();
            entityManager.createNamedQuery("PurchaseOrder.deleteAll").executeUpdate();
            entityManager.createNamedQuery("Customer.deleteAll").executeUpdate();
            entityManager.getTransaction().commit();

            if (databaseType.equals("the test")){            
                buildCustomers();
                buildPurchaseOrders();
            }        
        } finally {
            //Get View Window and Perform refresh...
            TopComponentOperator tco=  new TopComponentOperator("CustomerList Window");        
            JTreeOperator tree = new JTreeOperator(tco);     
            TreePath root=tree.findPath("");
            JPopupMenuOperator popup = new JPopupMenuOperator(tree.callPopupOnPath(root));
            popup.pushMenu("Reload");
        }
    }        
}
