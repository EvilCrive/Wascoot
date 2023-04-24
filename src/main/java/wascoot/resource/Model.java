/*
 * Copyright 2018-2023 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wascoot.resource;

import java.sql.Time;


public class Model {

	/**
	 * The badge number (identifier) of the employee
	 */
	//private final int badge;
	private final String name;

	/**
	 * The surname of the employee
	 */
	private final String brand;

	/**
	 * The age of the employee
	 */
	//private final int age;
	//private final String batteryLife;
	private final Time batteryLife;
	//private final LocalTime batteryLife;

	/**
	 * The salary of the employee
	 */
	private final double pricePerMin;


	public Model(final String name, final String brand, final Time batteryLife, final double pricePerMin) {
		this.name = name;
		this.brand = brand;
		this.batteryLife = batteryLife;
		this.pricePerMin = pricePerMin;
	}


	public final String getName() {
		return name;
	}


	public final String getBrand() {
		return brand;
	}


	public final Time getBatteryLife() {
		return batteryLife;
	}


	public final double getPricePerMin() {
		return pricePerMin;
	}

}
