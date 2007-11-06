package org.xwiki.xeclipse.editors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.editors.text.StorageDocumentProvider;
import org.xwiki.plugins.eclipse.XWikiEclipsePlugin;
import org.xwiki.xeclipse.model.IXWikiPage;
import org.xwiki.xeclipse.model.XWikiConnectionException;

public class XWikiPageDocumentProvider extends StorageDocumentProvider
{
    private XWikiPageEditor xwikiPageEditor;

    public XWikiPageDocumentProvider(XWikiPageEditor xwikiPageEditor)
    {
        this.xwikiPageEditor = xwikiPageEditor;
    }

    @Override
    protected IDocument createDocument(Object element) throws CoreException
    {
        IDocument document = new Document();
        if (element instanceof XWikiPageEditorInput) {
            XWikiPageEditorInput input = (XWikiPageEditorInput) element;
            document.set(input.getXWikiPage().getContent());            
        }

        return document;
    }

    @Override
    public boolean isModifiable(Object element)
    {
        return true;
    }
 
    @Override
    public boolean isReadOnly(Object element)
    {
        return false;
    }

    @Override
    protected void doSaveDocument(IProgressMonitor monitor, Object element, IDocument document,
        boolean overwrite) throws CoreException
    {
        XWikiPageEditorInput input = (XWikiPageEditorInput) element;
        IXWikiPage xwikiPage = input.getXWikiPage();

        xwikiPage.setContent(document.get());

        try {            
            xwikiPage.save();     
            
            if(xwikiPage.isConflict()) {
                MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Page out of synch", "The page being saved has been modified remotely, and is not up to date.\nLocal and remote content will be presented in the editor.\n\nMerge the contents and resave the page in order to actualy update the remote version.");
            }
            
            XWikiPageEditor.CaretState caretState = xwikiPageEditor.getCaretState();                
            document.set(input.getXWikiPage().getContent());                                    
            xwikiPageEditor.updateEditor(input.getXWikiPage());
            xwikiPageEditor.setCaretOffset(caretState);
        } catch (XWikiConnectionException e) {
            throw new CoreException(new Status(IStatus.ERROR, XWikiEclipsePlugin.PLUGIN_ID, "Unable to save", e));            
        }
    }

}
