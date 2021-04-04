package gui;
import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MedicionesLoadFileFilter extends FileFilter{

	@Override
	public boolean accept(File file) {
		if(file.isDirectory()){
			return true;
		}
		String name = file.getName();
		String extension = Utils.getFileExtension(name);
		if(extension == null){
			return false;
		}
		if(extension == null) {
			return false;
		}
		if(extension.equals("adm")) {
			return true;
		}
		
		return false;
	}

	@Override
	public String getDescription() {
		return "Archivo de mediciones (*.adm)";
	}

}
