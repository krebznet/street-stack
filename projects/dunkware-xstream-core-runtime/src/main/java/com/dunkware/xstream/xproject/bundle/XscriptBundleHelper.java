package com.dunkware.xstream.xproject.bundle;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.helpers.DFileHelper;
import com.dunkware.xstream.xproject.XScriptException;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.model.XScriptBundle;
import com.dunkware.xstream.xproject.model.XScriptFile;
import com.fasterxml.jackson.databind.ObjectMapper;

public class XscriptBundleHelper {

	public static XScriptBundle createBundleFromFileContents(String... contents) throws XScriptException {
		List<XScriptFile> files = new ArrayList<XScriptFile>();
		for (String fileContent : contents) {
			XScriptFile file = new XScriptFile(fileContent);
			files.add(file);
		}
		XScriptBundle bundle = new XScriptBundle();
		bundle.setFiles(files);
		return bundle;
	}

	public static XScriptBundle createBundleFromFilePaths(String... filePaths) throws XScriptException {
		List<XScriptFile> files = null;
		try {
			files = createFiles(filePaths);
		} catch (Exception e) {
			throw new XScriptException("Exception building XScriptFIles from filePaths " + e.toString());

		}
		XScriptBundle bundle = new XScriptBundle();
		bundle.setFiles(files);
		return bundle;

	}

	public static XScriptProject loadProject(XScriptBundle bundle) throws XScriptException {
		XScriptProject project = new XScriptProject(bundle);
		return project;
	}

	public String serialize(XScriptBundle bundle) throws XScriptException {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, bundle);
		} catch (Exception e) {
			throw new XScriptException("Serialize Bundle Exception " + e.toString(), e);
		}

		return mapper.toString();
	}

	public static List<XScriptFile> createFiles(String... filePaths) throws IOException {
		List<XScriptFile> files = new ArrayList<XScriptFile>();
		List<Path> paths = new ArrayList<Path>();
		for (String filePath : filePaths) {
			ScriptWalker walker = new ScriptWalker();
			Files.walkFileTree(Paths.get(filePath), walker);
			paths.addAll(walker.getPaths());
		}
		for (Path path : paths) {
			String content = DFileHelper.readFileContents(path.toAbsolutePath().toFile());
			XScriptFile file = new XScriptFile(content);
			files.add(file);
		}
		return files;
	}

	private static class ScriptWalker extends SimpleFileVisitor<Path> {

		private List<Path> paths = new ArrayList<Path>();

		public ScriptWalker() {

		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.xs");
			Path fuck = file.getFileName();
			if (matcher.matches(fuck) == false) {
				return FileVisitResult.CONTINUE;
			}

			paths.add(file);
			return FileVisitResult.CONTINUE;
		}

		public List<Path> getPaths() {
			return paths;
		}

	}

}
