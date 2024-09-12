package com.dunkware.utils.core.observable;

public class ObservableTest {

	public static class Dog extends Observable<Dog> { 
		
		private String name; 
		private int age;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
			propertyChange("name", name , name);
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		} 
		
		
		
	}
	
	
	public static void main(String[] args) {
		
		ObservableList<Dog> dogs = new ObservableList<Dog>();
		
		ObservableMonitor<Dog> dogListener = new ObservableMonitor<ObservableTest.Dog>() {

			@Override
			public void beanPropertyUpdate(Dog bean, String property, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				ObservableMonitor.super.beanPropertyUpdate(bean, property, oldValue, newValue);
			}

			@Override
			public void ListInsert(ObservableList<Dog> list, Dog bean) {
				// TODO Auto-generated method stub
				ObservableMonitor.super.ListInsert(list, bean);
			}

			@Override
			public void ListRemove(ObservableList<Dog> list, Dog bean) {
				// TODO Auto-generated method stub
				ObservableMonitor.super.ListRemove(list, bean);
			}
			
			
		};
	};
		
	}

