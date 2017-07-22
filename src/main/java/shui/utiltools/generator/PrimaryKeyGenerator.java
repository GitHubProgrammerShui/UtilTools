package shui.utiltools.generator;

public interface PrimaryKeyGenerator<T>{
	
	/**
	 * 生成主键
	 * @return 生成的主键
	 */
	T generatePrimaryKey();
}
