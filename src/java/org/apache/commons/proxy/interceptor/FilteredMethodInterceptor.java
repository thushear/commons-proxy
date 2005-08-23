/*
 *  Copyright 2005 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.commons.proxy.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Decorates another <code>MethodInterceptor</code> by only calling it if the
 * method is accepted by the supplied <code>MethodFilter</code>.
 *
 * @author James Carman
 * @version 1.0
 */
public class FilteredMethodInterceptor implements MethodInterceptor
{
    private final MethodInterceptor inner;
    private final MethodFilter filter;

    public FilteredMethodInterceptor( MethodInterceptor inner, MethodFilter filter )
    {
        this.inner = inner;
        this.filter = filter;
    }

    public Object invoke( MethodInvocation methodInvocation ) throws Throwable
    {
        if( filter.accepts( methodInvocation.getMethod() ) )
        {
            return inner.invoke( methodInvocation );
        }
        else
        {
            return methodInvocation.proceed();
        }
    }
}
