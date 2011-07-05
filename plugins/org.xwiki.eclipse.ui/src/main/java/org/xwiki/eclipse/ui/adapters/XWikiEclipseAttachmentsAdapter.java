/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
 */
package org.xwiki.eclipse.ui.adapters;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.WorkbenchAdapter;
import org.eclipse.ui.progress.IDeferredWorkbenchAdapter;
import org.eclipse.ui.progress.IElementCollector;
import org.xwiki.eclipse.model.XWikiEclipseAttachments;
import org.xwiki.eclipse.ui.UIConstants;
import org.xwiki.eclipse.ui.UIPlugin;

/**
 * @version $Id$
 */
public class XWikiEclipseAttachmentsAdapter extends WorkbenchAdapter implements IDeferredWorkbenchAdapter
{
    @Override
    public String getLabel(Object object)
    {
        if (object instanceof XWikiEclipseAttachments) {
            XWikiEclipseAttachments attachments = (XWikiEclipseAttachments) object;

            return attachments.getId();
        }

        return super.getLabel(object);
    }

    @Override
    public ImageDescriptor getImageDescriptor(Object object)
    {
        if (object instanceof XWikiEclipseAttachments) {
            return UIPlugin.getImageDescriptor(UIConstants.PAGE_ATTACHMENTS_ICON);
        }

        return null;
    }

    @Override
    public Object[] getChildren(Object object)
    {
        if (object instanceof XWikiEclipseAttachments) {
            XWikiEclipseAttachments attachments = (XWikiEclipseAttachments) object;
            return attachments.getAttachments().toArray();
        }
        return super.getChildren(object);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.progress.IDeferredWorkbenchAdapter#fetchDeferredChildren(java.lang.Object,
     *      org.eclipse.ui.progress.IElementCollector, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void fetchDeferredChildren(Object object, IElementCollector collector, IProgressMonitor monitor)
    {
        collector.add(getChildren(object), monitor);
        collector.done();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.progress.IDeferredWorkbenchAdapter#isContainer()
     */
    @Override
    public boolean isContainer()
    {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.progress.IDeferredWorkbenchAdapter#getRule(java.lang.Object)
     */
    @Override
    public ISchedulingRule getRule(Object object)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
