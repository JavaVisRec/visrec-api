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