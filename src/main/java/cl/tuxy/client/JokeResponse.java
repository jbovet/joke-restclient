/**
 * Copyright (C) 2016  Jose P. Bovet Derpich (jose.bovet@gmail.com)
 *  
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cl.tuxy.client;

import java.util.List;

public class JokeResponse {

	private String type;
	private List<Joke> value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Joke> getValue() {
		return value;
	}

	public void setValue(List<Joke> value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "JokeResponse [type=" + type + ", value=" + value + "]";
	}

}
