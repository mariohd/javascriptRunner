package engine;
import internationalization.Messages;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Loader {
	private ScriptEngineManager manager;
	private ScriptEngine engine;
	private File jsFile;
	
	public Loader(File js) {
		jsFile = js;
		manager = new ScriptEngineManager();
		engine = manager.getEngineByName("JavaScript");
	} 

	public Object execute(String fn, Object[] parameters) throws IOException, ScriptException, NoSuchMethodException {
		String script = putJsInString(jsFile, Charset.defaultCharset());
		engine.eval(script);
		Invocable inv = (Invocable) engine;

		return inv.invokeFunction(fn , parameters);
	}

	private static String putJsInString(File file, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
		return new String(encoded, encoding);
	}

	public String fileLoaded() {
		if ( jsFile == null ) {
			return Messages.getMessage("iO.fileNotLoaded");
		} else {
			return Messages.getMessage("iO.fileLoaded");
		}
	}
	
	public String getFileName() {
		if ( jsFile != null ) {
			return jsFile.getAbsolutePath();
		} else {
			return fileLoaded();
		}
	}
}
