/**
 * Copyright © 2010-2011 Atilika Inc.  All rights reserved.
 *
 * See the NOTICE.txt file distributed with this work for additional
 * information regarding copyright ownership.
 * 
 * Atilika Inc. licenses this file to you under the Apache License, Version
 * 2.0 (the "License"); you may not use this file except in compliance with
 * the License.  A copy of the License is distributed with this work in the
 * LICENSE.txt file.  You may also obtain a copy of the License from
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.atilika.kuromoji.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.atilika.kuromoji.dict.ConnectionCosts;


/**
 * @author Masaru Hasegawa
 * @author Christian Moen
 */
public class ConnectionCostsBuilder {
	
	public ConnectionCostsBuilder() {
		
	}
	
	public static ConnectionCosts build(String filename) throws IOException {
		FileInputStream inputStream = new FileInputStream(filename);
		InputStreamReader streamReader = new InputStreamReader(inputStream);
		LineNumberReader lineReader = new LineNumberReader(streamReader);

		String line = lineReader.readLine();
		String[] dimensions = line.split("\\s+");
		
		assert dimensions.length == 3;

		int leftSize = Integer.parseInt(dimensions[0]);
		int rightSize = Integer.parseInt(dimensions[1]);
		
		assert leftSize > 0 && rightSize > 0;
		
		ConnectionCosts costs = new ConnectionCosts(leftSize, rightSize);
		
		while ((line = lineReader.readLine()) != null) {
			String[] fields = line.split("\\s+");

			assert fields.length == 3;
			
			int leftId = Integer.parseInt(fields[0]);
			int rightId = Integer.parseInt(fields[1]);
			int cost = Integer.parseInt(fields[2]);

			costs.add(leftId, rightId, cost);
		}
		return costs;
	}
}
