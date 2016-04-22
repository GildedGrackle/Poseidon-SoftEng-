package poseidon.common.view;

/**
 *  Boundary objects that change appearance when the underlying model changes.
 *  
 * @author Alex Titus
 */
public interface IModelUpdated
{
	/**
	 *  Updates display when underlying model changes.
	 *  
	 *  @return  indication of whether the operation completed successfully
	 */
	public Boolean modelUpdated();
}
