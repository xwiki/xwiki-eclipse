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
package org.xwiki.eclipse.ui.actions;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.WorkingSetFilterActionGroup;
import org.eclipse.ui.navigator.CommonActionProvider;

/**
 * @version $Id$
 */
public class WorkingSetActionProvider
{
    private WorkingSetFilterActionGroup actions;

    private boolean contributedToViewMenu;

    private Viewer viewer;
    
    public WorkingSetActionProvider(Viewer viewer)
    {
        this.viewer = viewer;
    }
    
    public void fillActionBars(IActionBars actionBars)
    {
        if (!contributedToViewMenu) {
            actions =
                new WorkingSetFilterActionGroup(Display.getDefault().getActiveShell(), new IPropertyChangeListener()
                {
                    public void propertyChange(PropertyChangeEvent event)
                    {
                        if (event.getNewValue() != null) {
                            viewer.setInput(event.getNewValue());
                        } else {
                            /* Actually it doesn't really matter what input we specify here */
                            viewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
                        }
                    }
                });

            actions.fillActionBars(actionBars);

            contributedToViewMenu = true;
        }
    }
}
