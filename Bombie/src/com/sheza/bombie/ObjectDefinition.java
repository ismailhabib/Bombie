package com.sheza.bombie;

public class ObjectDefinition {

	private String filename;

	private ObjectType type;

	private boolean isTiled = false;

	private int width;

	private int height;

	private int nbOfColumns;

	private int nbOfRows;

	private boolean animated = false;

	private boolean moveable = true;

	private boolean rotateable = false;

	public String getFilename() {
		return filename;
	}

	public boolean isTiled() {
		return isTiled;
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

	public ObjectType getType() {
		return type;
	}

	public boolean isAnimated() {
		return animated;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public boolean isRotateable() {
		return rotateable;
	}

	public static class Builder {
		private String filename;
		private ObjectType type;
		private boolean isTiled;
		private int width;
		private int height;
		private int nbOfColumns;
		private int nbOfRows;
		private boolean animated;
		private boolean moveable;
		private boolean rotateable;

		public Builder filename(String filename) {
			this.filename = filename;
			return this;
		}

		public Builder type(ObjectType type) {
			this.type = type;
			return this;
		}

		public Builder isTiled(boolean isTiled) {
			this.isTiled = isTiled;
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

		public Builder animated(boolean animated) {
			this.animated = animated;
			return this;
		}

		public Builder moveable(boolean moveable) {
			this.moveable = moveable;
			return this;
		}

		public Builder rotateable(boolean rotateable) {
			this.rotateable = rotateable;
			return this;
		}

		public ObjectDefinition build() {
			return new ObjectDefinition(this);
		}
	}

	private ObjectDefinition(Builder builder) {
		this.filename = builder.filename;
		this.type = builder.type;
		this.isTiled = builder.isTiled;
		this.width = builder.width;
		this.height = builder.height;
		this.nbOfColumns = builder.nbOfColumns;
		this.nbOfRows = builder.nbOfRows;
		this.animated = builder.animated;
		this.moveable = builder.moveable;
		this.rotateable = builder.rotateable;
	}
}
