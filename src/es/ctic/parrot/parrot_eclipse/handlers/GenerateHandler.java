package es.ctic.parrot.parrot_eclipse.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.text.IDocument;
import es.ctic.parrot.parrot_eclipse.ParrotEclipsePlugin;
import es.ctic.parrot.parrot_eclipse.views.DocBrowserView;

public class GenerateHandler extends AbstractHandler {

	public GenerateHandler() {		
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		IWorkbenchPage page = window.getActivePage();
		DocBrowserView browserView;
		
		try {
			browserView = (DocBrowserView)page.showView(DocBrowserView.ViewID);
		}
		catch (PartInitException e) {
			String msg = "Browser view not available";
			Status status = new Status(Status.ERROR, ParrotEclipsePlugin.PLUGIN_ID, msg);
			ParrotEclipsePlugin.getDefault().getLog().log(status);
			return null;
		}
		
		IEditorPart part = page.getActiveEditor();
		
		if(part != null) {
			// TODO: Verify casting
			ITextEditor editor = (ITextEditor)part;
			IDocumentProvider dp = editor.getDocumentProvider();
			IDocument doc = dp.getDocument(editor.getEditorInput());
			
			String documentationPage = ParrotEclipsePlugin.getDefault().parrotCore.exec(doc.get());
			browserView.setBrowserHTML(documentationPage);
		}
		else {
			String msg = "No active editor, please select one";
			Status status = new Status(Status.ERROR, ParrotEclipsePlugin.PLUGIN_ID, msg);
			ParrotEclipsePlugin.getDefault().getLog().log(status);
			browserView.setBrowserHTML("<html><body>See error log</body></html>");
		}
		
		return null;
	}
}
