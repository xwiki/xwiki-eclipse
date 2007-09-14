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

package org.xwiki.plugins.eclipse.model.adapters;

import org.eclipse.swt.graphics.Image;

/**
 * Interface implemented by all nodes participating in the tree viewer of XWikiNavigator.
 */
public interface TreeAdapter
{
    /**
     * @return An image representing this node in the tree UI.
     */
    public Image getImage();

    /**
     * @return A textual description of this node in the tree UI.
     */
    public String getText();

    /**
     * @return Parent of this node.
     */
    public Object getTreeParent();

    /**
     * @return Whether this node has more children.
     */
    public boolean hasChildren();

    /**
     * @return An array of nodes which are underneath this node.
     */
    public Object[] getTreeChildren();
}
