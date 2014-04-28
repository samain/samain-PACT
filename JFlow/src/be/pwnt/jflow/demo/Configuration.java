/*
 * JFlow
 * Created by Tim De Pauw <http://pwnt.be/>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
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

package be.pwnt.jflow.demo;

import java.awt.Color;
import java.io.IOException;

import be.pwnt.jflow.Shape;
import be.pwnt.jflow.shape.Picture;

public class Configuration extends be.pwnt.jflow.Configuration {
	public Configuration() {
		shapes = new Shape[5];
		/*
		shapes = new Shape[9];
		for (int i = 0; i < shapes.length; i++) {
			try {
				shapes[i] = new Picture(getClass().getResource(
						"img/pic" + (i + 1) + ".jpg"));
			} catch (IOException e) {
			}
		}
		*/
		try {
			shapes[0] = new Picture(getClass().getResource(
					"img/kaiken.jpg"));
			shapes[1] = new Picture(getClass().getResource(
					"img/heidi.jpg"));
			shapes[2] = new Picture(getClass().getResource(
					"img/petit-prince.jpg"));
			shapes[3] = new Picture(getClass().getResource(
					"img/bfg.jpg"));
			shapes[4] = new Picture(getClass().getResource(
					"img/voyage.jpg"));
				
		} catch (Exception e) {
			System.out.println("Exception a l'ouverture du cover Flow : " + e.getMessage());
		}
		
		//backgroundColor = new Color(255, 255, 255, 0);
	}
}
