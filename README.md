# Ubeneat

This project refers to food delivery apps like Uber Eats and foodpanda, and use Java, PHP, and MySQL to develop.  
Ubeneat_app is an android application, and Ubeneat_web is based on the website.  
The UI of the website is not pretty because it's my "Hello World" project.
You can do better UI/UX on the front end. :&nbsp; )

## Database schema：
![image](https://github.com/benntust/Ubeneat/blob/master/img/schema.jpg)

## Function set：  
### Customer side:
1.  A customer can register/login into system.  
2.  A customer can order the meals from menu.  
3. A customer can partial search meal by categories, store names, or meal names.  
4. A customer can delete the previous order before the order is picked up. (state<3)  
5. A customer can update the previous order before the order is picked up. (state<3)  
6. A customer can see the status of the orders. (state1~4)  
7. A customer can make the order completed (state5) after the order arrived. (state4)  
### Store side:  
1. A store can insert selling meals. (The images stored by URL link)  
2. A store can delete selling meals and prices.  
3. A store can update selling meals and prices.  
### Delivery side:  
1. A delivery man can query available orders uncompleted. (state<5)  
2. A delivery man can select available orders to pick up. (state2)  
3. A delivery man reports the status of the orders. (state3~4) 
## User interface： 
![image](https://github.com/benntust/Ubeneat/blob/master/img/UI.jpg)
