package hu.montlikadani.tablist.Sponge;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.spongepowered.api.config.DefaultConfig;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public class ConfigManager {

	private String path;
	private String name;
	private File file;

	@Inject
	@DefaultConfig(sharedRoot = true)
	private Path configPath;

	@Inject
	private CommentedConfigurationNode node;

	@Inject
	@DefaultConfig(sharedRoot = true)
	private ConfigurationLoader<CommentedConfigurationNode> loader;

	public ConfigManager(String path, String name) {
		this.path = path;
		this.name = name;

		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		this.file = new File(folder + File.separator + name);
		this.loader = HoconConfigurationLoader.builder().setFile(file).build();
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public File getFile() {
		return file;
	}

	@Inject
	public Path getConfigPath() {
		return configPath;
	}

	@Inject
	public CommentedConfigurationNode getConfigNode() {
		return node;
	}

	@Inject
	public ConfigurationLoader<CommentedConfigurationNode> getConfigLoader() {
		return loader;
	}

	public void createFile() {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		load();
		save();
	}

	public CommentedConfigurationNode get(Object... path) {
		return node.getNode(path);
	}

	public void set(Object value, Object... path) {
		if (!contains(path)) {
			return;
		}

		load();
		get(path).setValue(value);
		save();
	}

	public void setComment(String comment, Object... path) {
		if (comment != null && !comment.isEmpty()) {
			get(path).setComment(comment);
			load();
			save();
		}
	}

	public boolean getBoolean(Object... path) {
		return get(path).getBoolean();
	}

	public boolean getBoolean(Boolean defValue, Object... path) {
		set(defValue, path);
		return get(path).getBoolean(defValue);
	}

	public int getInt(Object... path) {
		return get(path).getInt();
	}

	public int getInt(Integer defValue, Object... path) {
		set(defValue, path);
		return get(path).getInt(defValue);
	}

	public String getString(Object... path) {
		return get(path).getString();
	}

	public String getString(String defValue, Object... path) {
		set(defValue, path);
		return get(path).getString(defValue);
	}

	public List<String> getStringList(Object... path) {
		return getStringList(null, path);
	}

	public List<String> getStringList(List<String> def, Object... path) {
		set(def, path);

		try {
			if (def == null) {
				return get(path).getList(TypeToken.of(String.class));
			}

			return get(path).getList(TypeToken.of(String.class), def);
		} catch (ObjectMappingException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean contains(Object... path) {
		return get(path).isVirtual();
	}

	public boolean isStringExistsAndNotEmpty(Object... path) {
		return contains(path) && !get(path).getString().isEmpty();
	}

	public boolean isString(Object... path) {
		Object val = get(path).getValue();
		return val instanceof String;
	}

	public boolean isList(Object... path) {
		Object val = get(path).getValue();
		return val instanceof List;
	}

	public void save() {
		try {
			loader.save(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load() {
		try {
			node = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}