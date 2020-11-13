package com.vinayakborhade.hibernateDemo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("hibernateDemo");


    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
		addCustomer(1, "tony", "stark");
		addCustomer(2, "Bruce", "Banner");
		addCustomer(3, "Steve", "Rogers");
    	getCustomers();
        return "Got it!";
    }
    
	
	public static void addCustomer(int id, String firstName, String lastName) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			Customer cust = new Customer();
			cust.setId(id);
			cust.setFirstName(firstName);
			cust.setLastName(lastName);
			em.persist(cust);
			et.commit();
		}
		catch(Exception ex) {
			if(et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
	}

    
	public static void getCustomers() {
		EntityManager em = entityManagerFactory.createEntityManager();
		String query = "Select c from Customer c where c.id is not null";
		TypedQuery<Customer> tq = em.createQuery(query, Customer.class);
//		tq.setParameter("custID", id);
		List<Customer> custs;
		try {
			custs = tq.getResultList();	
			custs.forEach(cust -> 
				System.out.println(cust.getFirstName() + " " + cust.getLastName()));
		}
		catch(NoResultException ex) {
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		finally {
			em.close();
		}
	}

}
