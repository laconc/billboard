/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billboard;

/**
 *
 * @author vita
 */
public class Items {
 
   private Long id;
   private String items;
   private int x;
   private int y;
   private Double percent;
 
   public Items(Long id, String items, int x, int y, Double percent) {
       this.id = id;
       this.items = items;
       this.x = x;
       this.y = y;
       this.percent = percent;
       
   }
 
   public Long getId() {//1
       return id;
   }
 
   public void setId(Long id) {//1
       this.id = id;
   }
 
   public String getItems() {//2
       return items;
   }
 
   public void setItems(String items) {//2
       this.items = items;
   }
 
   public Integer getX() {//3
       return x;
   }
 
   public void setX(Integer x) {//3
       this.x = x;
   }
 
   public Integer getY() {//4
       return y;
   }
 
   public void setY(Integer y) {//4
       this.y = y;
   }
 
   public Double getPercent() {//5
       return percent;
   }
 
   public void setPercent(Double percent) {//5
       this.percent = percent;
   }
}