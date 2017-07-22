package shui.utiltools.generator;

public interface LengthPrimaryKeyGenerator<T> extends PrimaryKeyGenerator<T>{
	/**
	 * 生成主键，按照指定的长度生成主键
	 * @param length
	 * @return
	 */
	T generatePrimaryKey(int length);
}
