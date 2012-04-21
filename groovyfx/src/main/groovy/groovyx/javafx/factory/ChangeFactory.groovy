/*
* Copyright 2012 the original author or authors.
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
package groovyx.javafx.factory

import groovyx.javafx.event.*
import javafx.beans.InvalidationListener
import javafx.beans.value.ChangeListener

/**
*
* @author jimclarke
*/
class ChangeFactory extends AbstractFXBeanFactory {
    
    ChangeFactory(Class beanClass) {
        super(beanClass);
    }
    
    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes)
            throws InstantiationException, IllegalAccessException {
         def listener = null;
         if(ChangeListener.isAssignableFrom(beanClass)) {
             listener = new GroovyChangeListener(value?value:name)
         }else {
             listener = new GroovyInvalidationListener(value?value:name)
         }   
         listener
    }
    
    public boolean isHandlesNodeChildren() {
        return true;
    }

    public boolean onNodeChildren(FactoryBuilderSupport builder, Object node, Closure childContent) {
        node.closure = childContent
        return false
    }
	
}

