package com.sheza.bombie;

public class LevelDefinition {

	private String filename;

	private int width;

	private int height;

	private int nbOfColumns;

	private int nbOfRows;

	public String getFilename() {
		return filename;
	}

	public int getNbOfColumns() {
		return nbOfColumns;
	}

	public int getNbOfRows() {
		return nbOfRows;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static class Builder {
		private String filename;
		private int width;
		private int height;
		private int nbOfColumns;
		private int nbOfRows;

		public Builder filename(String filename) {
			this.filename = filename;
			return this;
		}

		public Builder width(int width) {
			this.width = width;
			return this;
		}

		public Builder height(int height) {
			this.height = height;
			return this;
		}

		public Builder nbOfColumns(int nbOfColumns) {
			this.nbOfColumns = nbOfColumns;
			return this;
		}

		public Builder nbOfRows(int nbOfRows) {
			this.nbOfRows = nbOfRows;
			return this;
		}

		public LevelDefinition build() {
			return new LevelDefinition(this);
		}
	}

	private LevelDefinition(Builder builder) {
		this.filename = builder.filename;
		this.width = builder.width;
		this.height = builder.height;
		this.nbOfColumns = builder.nbOfColumns;
		this.nbOfRows = builder.nbOfRows;
	}
}
