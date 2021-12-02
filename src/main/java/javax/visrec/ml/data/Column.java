/**
 * Visual Recognition API for Java, JSR381
 * Copyright (C) 2020  Zoran Sevarac, Frank Greco
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
 
package javax.visrec.ml.data;

/**
 * Column in a data set.
 * 
 * @see DataSet
 */
  public class Column {
        private String name;
        private Type type;
        private boolean isTarget;

        public Column(String name) {
            this.name = name;
            this.type = null;
            this.isTarget = false;
        }
        
        public Column(String name, Type type) {
            this.name = name;
            this.type = type;
            this.isTarget = false;
        }        

        public Column(String name, Type type, boolean isTarget) {
            this.name = name;
            this.type = type;
            this.isTarget = isTarget;
        }

        public String getName() {
            return name;
        }

        public Type getType() {
            return type;
        }

        public boolean isTarget() {
            return isTarget;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public void setAsTarget(boolean isTarget) {
            this.isTarget = isTarget;
        }
                
        /**
         * Column data type
         */        
        public static enum Type {
            DECIMAL, INTEGER, BINARY, ENUM, STRING;
        }        
    }