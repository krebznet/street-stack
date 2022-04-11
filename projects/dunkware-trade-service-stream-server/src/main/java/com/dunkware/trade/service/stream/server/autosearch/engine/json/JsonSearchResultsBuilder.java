package com.dunkware.trade.service.stream.server.autosearch.engine.json;

public class JsonSearchResultsBuilder {

	public static JsonSearchResultsBuilder newBuilder() {
		return new JsonSearchResultsBuilder();

	}

	private JsonSearchResultsBuilder() {

	}

	public JsonSearchResultsBuilder.CategoryBuilder addCategory(String name, String category) {
		CategoryBuilder cbuilder = new CategoryBuilder(this, name, category);
		return cbuilder;
	}

	protected static JsonSearchResults results = new JsonSearchResults();

	private static void doAddCategory(JsonSearchCategory category) {
		results.getCategories().add(category);

	}
	
	public static JsonSearchResults build() { 
		return results;
	}

	public  class CategoryBuilder {

		private JsonSearchResultsBuilder builderParent;

		private JsonSearchCategory category = new JsonSearchCategory();

		public CategoryBuilder(JsonSearchResultsBuilder builderParent, String name, String categoryType) {
			category.setCategoryName(categoryType);
			category.setCategoryType(categoryType);
			this.builderParent = builderParent;
		}

		public CategoryBuilder addElement(int id, String identifier, String name) {
			JsonSearchElement element = new JsonSearchElement();
			element.id = id;
			element.identifier = identifier;
			element.name = name;
			category.getElements().add(element);

			return CategoryBuilder.this;
		}

		public JsonSearchResultsBuilder build() {
			doAddCategory(category);
			return builderParent;
		}
	}

}
