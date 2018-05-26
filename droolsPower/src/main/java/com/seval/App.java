package com.seval;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * Hello world!
 *
 */
public class App 
{
	 private static final String KEY_ID = "id";
	  private static final String KEY_PRODUCT_TYPE = "productType";
	  private static final String KEY_DISCOUNT_RATE = "normalDiscountRate";
	  private static final String KEY_BULK_DISCOUNT_QTY = "bulkDiscountQty";
	  private static final String KEY_BULK_DISCOUNT_RATE = "bulkDiscountRate";
	 
	  
    public static void main( String[] args ) throws IOException 
    {
    	List<RuleMap<String, Object>> ruleAttributes =  createRuleMap();
    	KnowledgeBase kbase = KnowledgeBaseBuilder.build(ruleAttributes);
    	StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
    	 List<OrderItem> orderItems = getOrderItems();
    	 
    	    for (OrderItem orderItem : orderItems) {
    	      System.out.println("Before firing the rules:" + orderItem);
    	 
    	      ksession.insert(orderItem);
    	      ksession.fireAllRules();
    	      System.out.println("After firing the rules:" + orderItem);
    	    }
    	 
    	    ksession.dispose();
    }

    /**
     * Create some OrderItems 
     * @return
     */
    private static List<OrderItem> getOrderItems() {
      OrderItem orderItem1 = new OrderItem();
      orderItem1.setType(OrderItem.PROD_TYPE.BASIC);
      orderItem1.setUnits(10);
      orderItem1.setUnitPrice(BigDecimal.valueOf(2.50));
   
      OrderItem orderItem2 = new OrderItem();
      orderItem2.setType(OrderItem.PROD_TYPE.LUXURY);
      orderItem2.setUnits(12);
      orderItem2.setUnitPrice(BigDecimal.valueOf(5.00));
   
      List<OrderItem> listOfItems = new ArrayList<OrderItem>();
      listOfItems.add(orderItem1);
      listOfItems.add(orderItem2);
   
      return listOfItems;
    }
	
	 /**
	   * In commercial apps, can be read from a database table or Excel spreadsheet
	   * @return
	   */
	  private static List<RuleMap<String, Object>> createRuleMap() {
	    RuleMap<String, Object> ruleSet1 = new RuleMap<String, Object>();
	    ruleSet1.put(KEY_ID, 1);
	    ruleSet1.put(KEY_PRODUCT_TYPE, OrderItem.PROD_TYPE.BASIC);
	    ruleSet1.put(KEY_DISCOUNT_RATE, 0.1);
	    ruleSet1.put(KEY_BULK_DISCOUNT_QTY, 5);
	    ruleSet1.put(KEY_BULK_DISCOUNT_RATE, 0.2);
	 
	    RuleMap<String, Object> ruleSet2 = new RuleMap<String, Object>();
	    ruleSet2.put(KEY_ID, 2);
	    ruleSet2.put(KEY_PRODUCT_TYPE, OrderItem.PROD_TYPE.LUXURY);
	    ruleSet2.put(KEY_DISCOUNT_RATE, 0.2);
	    ruleSet2.put(KEY_BULK_DISCOUNT_QTY, 10);
	    ruleSet2.put(KEY_BULK_DISCOUNT_RATE, 0.3);
	 
	    List<RuleMap<String, Object>> rules = new ArrayList<RuleMap<String, Object>>();
	    rules.add(ruleSet1);
	    rules.add(ruleSet2);
	 
	    return rules;
	  }
}
