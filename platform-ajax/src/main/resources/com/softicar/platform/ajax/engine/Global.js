/**
 * This class connects the different Javascript modules.
 * <p>
 * This class and the implied concept is deprecated.
 */
function Global()
{
	this.encoder = new Encoder();
	this.context = _DOM_CONTEXT_;

	this.addValueNode = function(nodeID, node) { m_valueNodeMap.addValueNode(nodeID, node); };
	this.removeValueNode = function(nodeID) { m_valueNodeMap.removeValueNode(nodeID); };
	this.copyNodeValues = function(data) { m_valueNodeMap.copyNodeValues(data); };
	this.approveNodeValues = function() { m_valueNodeMap.approveNodeValues(); };
	this.isValueChanged = function(node) { return m_valueNodeMap.isValueChanged(node); };
	
	this.setValue = function(node, value) { m_valueNodeMap.setValue(node, value); };
	this.setChecked = function(node, checked) { m_valueNodeMap.setChecked(node, checked); };
	this.setSelectedOptions = function(node, optionIDs) { m_valueNodeMap.setSelectedOptions(node, optionIDs); };

	this.getNode = function(nodeID) { return _DOM_CONTEXT_.getNode(nodeID); };

	this.fade = function(node, target) { m_fadingContext.fade(node, target); };

	this.scheduleKeepAlive = function() { m_keepAlive.schedule(); };
	
	var m_valueNodeMap = new ValueNodeMap();
	var m_keepAlive = new KeepAlive(3*60*1000);
	var m_fadingContext = new FadingContext();
}

var GLOBAL;

function GLOBAL_init()
{
	GLOBAL = new Global();
	GLOBAL.scheduleKeepAlive();
}