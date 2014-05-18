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
		
		try {
			shapes[0] = new Picture("Voyages de Gulliver -Espace-.txt" ,getClass().getResource(
					"img/gulliver.jpg"));
			shapes[1] = new Picture("Histoires du Grand Nord -Plage-.txt" ,getClass().getResource(
					"img/histoire-grand-nord.jpg"));
			shapes[2] = new Picture("Les raisins de la colère -Nuit-.txt" ,getClass().getResource(
					"img/raisins.png"));
			shapes[3] = new Picture("Les raisins de la colère -Bal-.txt" ,getClass().getResource(
					"img/Les raisins de la colere.jpg"));
			shapes[4] = new Picture("Voyages de Gulliver -Ville-.txt" ,getClass().getResource(
					"img/gulliver2.jpg"));
				
		} catch (Exception e) {
			System.out.println("Exception a l'ouverture du cover Flow : " + e.getMessage());
		}
	}
}
