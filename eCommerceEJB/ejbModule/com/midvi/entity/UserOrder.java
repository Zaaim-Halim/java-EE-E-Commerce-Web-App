package com.midvi.entity;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
public class UserOrder implements Serializable {
    
	private static final long serialVersionUID = 1L;  
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "order_date")
	@Temporal(TemporalType.DATE)
	private Date orderDate;
 
    private int orderNumb;
    @NotNull
    private String status;
    @Column(name = "quantity")
    private int quantity;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
    private User user;
  
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private OrderDetail orderDetail;
   
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Product product;
    
	public UserOrder() {
		super();
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderNumb() {
		return orderNumb;
	}

	public void setOrderNumb(int orderNumb) {
		this.orderNumb = orderNumb;
	}



	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	
}
